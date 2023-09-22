#!/usr/bin/python3
from flask import Flask, render_template, request, redirect, flash, url_for
from psycopg_pool import ConnectionPool
from datetime import datetime
import psycopg
import os
import re

email_regex = re.compile(
    r'([A-Za-z0-9]+[.-_])*[A-Za-z0-9]+@[A-Za-z0-9-]+(\.[A-Z|a-z]{2,})+')
float_regex = re.compile(r'\d{0,8}[.,]\d{0,2}')
address_regex = re.compile(r'[A-Za-z0-9 ]+, \d{4}-\d{3} [A-Za-z ]+')
phone_regex = re.compile(r'\+*[0-9]+\s?[0-9]+')

DATABASE_URL = os.environ.get(
    "DATABASE_URL", "postgres://tester:tester@postgres/testerdb")
pool = ConnectionPool(conninfo=DATABASE_URL)

app = Flask(__name__)
app.secret_key = "12345"


@app.route("/", methods=("GET",))
def home():
    elements = [
        {
            "isTop": 1,
            "url": "/gerir-clientes",
            "title": "Gerir Clientes"
        },
        {
            "isTop": 1,
            "url": "/gerir-produtos",
            "title": "Gerir Produtos"
        },
        {
            "isTop": 1,
            "url": "/gerir-fornecedores",
            "title": "Gerir Fornecedores"
        },
        {
            "isTop": 0,
            "url": "/gerir-encomendas",
            "title": "Gerir Encomendas"
        },
        {
            "isTop": 0,
            "url": "/realizar-encomenda",
            "title": "Realizar Encomenda"
        }
    ]
    return render_template('index.html', elements=elements)


@app.route("/gerir-clientes", methods=("GET",))
def gerir_clientes():
    with pool.connection() as conn:
        with conn.cursor() as cursor:
            cursor.execute(
                "SELECT cust_no, name FROM customer WHERE NOT email LIKE '%%deleted';")

            def parser(el): return {"id": el[0], "name": el[1]}
            clients = list(map(parser, cursor.fetchall()))
            psize = 5
            pcount = len(clients) // psize + 1 if len(clients) % psize != 0 else len(clients) // psize
    return render_template("gerir-clientes.html", clients=clients, pcount=pcount, psize=psize)


@app.route("/gerir-clientes/<cust_no>/delete", methods=("POST",))
def gerir_clientes_delete(cust_no):
    with pool.connection() as conn:
        with conn.cursor() as cursor:
            email = cust_no + "deleted"
            cursor.execute("UPDATE customer SET name = 'deleted', email = %(email)s, phone = NULL, ADDRESS = NULL WHERE cust_no = %(cust_no)s;", {
                           "cust_no": cust_no, "email": email})
    return redirect("/gerir-clientes")


@app.route("/gerir-clientes/add", methods=("POST",))
def gerir_clientes_add():
    name = request.form["name"]
    email = request.form["email"]
    phone = request.form["phone"] if request.form["phone"] else None
    address = request.form["address"] if request.form["address"] else None
    error = ""

    if not name or len(name) > 80:
        error = "Nome inválido"
    elif not email or not re.fullmatch(email_regex, email) or len(email) > 254:
        error = "Email inválido"
    elif phone is not None and (not re.fullmatch(phone_regex, phone) or len(phone) > 15):
        error = "Telemóvel inválido"
    elif address is not None and (not re.fullmatch(address_regex, address) or len(address) > 255):
        error = "Morada inválida"

    if error:
        flash(error)
    else:
        with pool.connection() as conn:
            with conn.cursor() as cursor:
                cursor.execute("START TRANSACTION;")
                existing_email = cursor.execute(
                    "SELECT email FROM customer WHERE email = %(email)s;", {"email": email}).fetchone()
                if existing_email:
                    flash("O email já existe")
                    cursor.execute("ROLLBACK;")
                    return redirect("/gerir-clientes")

                cust_no = cursor.execute(
                    "SELECT MAX(cust_no) FROM customer;").fetchone()[0]
                if not cust_no:
                    cust_no = 1
                else:
                    cust_no += 1

                cursor.execute(
                    """
                    INSERT INTO customer VALUES
                    (%(cust_no)s, %(name)s, %(email)s, %(phone)s, %(address)s);
                    """,
                    {"cust_no": cust_no, "name": name, "email": email, "phone": phone, "address": address})
                cursor.execute("COMMIT;")

    return redirect("/gerir-clientes")


@app.route("/gerir-produtos", methods=("GET",))
def gerir_produtos():
    with pool.connection() as conn:
        with conn.cursor() as cursor:
            cursor.execute("SELECT SKU, name FROM product;")
            def parser(el): return {"id": el[0], "name": el[1]}
            products = list(map(parser, cursor.fetchall()))
            psize = 5
            pcount = len(products) // psize + 1 if len(products) % psize != 0 else len(products) // psize
    return render_template("gerir-produtos.html", products=products, pcount=pcount, psize=psize)


@app.route("/gerir-produtos/<sku>/delete", methods=("POST",))
def gerir_produtos_delete(sku):
    with pool.connection() as conn:
        with conn.cursor() as cursor:
            cursor.execute("START TRANSACTION;")
            cursor.execute(
                "UPDATE supplier SET sku = NULL WHERE sku = %(sku)s;", {"sku": sku})
            cursor.execute(
                "DELETE FROM contains WHERE sku = %(sku)s;", {"sku": sku})
            cursor.execute(
                """
                DELETE FROM process
                WHERE NOT order_no IN
                (SELECT DISTINCT order_no FROM contains)
                """)
            cursor.execute(
                """
                DELETE FROM pay
                WHERE NOT order_no IN
                (SELECT DISTINCT order_no FROM contains)
                """)
            cursor.execute(
                """
                DELETE FROM orders
                WHERE NOT order_no IN
                (SELECT DISTINCT order_no FROM contains)
                """)
            cursor.execute(
                "DELETE FROM product WHERE sku = %(sku)s;", {"sku": sku})
            cursor.execute("COMMIT;")
    return redirect("/gerir-produtos")


@app.route("/gerir-produtos/<sku>/edit", methods=("POST", "GET"))
def gerir_produtos_edit(sku):
    if request.method == "GET":
        with pool.connection() as conn:
            with conn.cursor() as cursor:
                cursor.execute(
                    "SELECT * FROM product WHERE sku = %(sku)s;", {"sku": sku})

                def parser(el): return {"sku": el[0], "name": el[1],
                                        "description": el[2] if el[2] != "NULL" else "",
                                        "price": el[3],
                                        "ean": el[4] if el[4] != "NULL" else ""}
                product = parser(cursor.fetchone())
        return render_template("editar-produto.html", product=product)
    else:
        name = request.form["name"]
        description = request.form["description"] if request.form["description"] else None
        price = request.form["price"]
        ean = request.form["ean"] if request.form["ean"] else None
        error = ""

        if not name or len(name) > 200:
            error = "Nome inválido"
        elif not price or (not re.fullmatch(float_regex, price) and not price.isnumeric()) or float(price) < 0:
            error = "Preço inválido"
        elif ean is not None and (not ean.isnumeric() or len(ean) != 13):
            error = "EAN inválido"

        if error:
            flash(error)
        else:
            with pool.connection() as conn:
                with conn.cursor() as cursor:
                    cursor.execute("START TRANSACTION;")
                    existing_sku = cursor.execute(
                        "SELECT sku FROM product WHERE sku = %(sku)s;", {"sku": sku}).fetchone()
                    if not existing_sku:
                        error = "O SKU não existe"
                    elif ean != None:
                        existing_ean = cursor.execute("SELECT ean FROM product WHERE ean = %(ean)s AND NOT sku = %(sku)s;", {
                                                      "ean": ean, "sku": sku}).fetchone()
                        if existing_ean:
                            error = "O EAN já existe"

                    if error:
                        flash(error)
                    else:
                        cursor.execute(
                            """
                            UPDATE product SET
                            name = %(name)s, description = %(description)s, price = %(price)s, ean = %(ean)s
                            WHERE sku = %(sku)s;
                            """,
                            {"sku": sku, "name": name, "description": description, "price": price, "ean": ean})
                    cursor.execute("COMMIT;")

        return redirect("/gerir-produtos")


@app.route("/gerir-produtos/add", methods=("POST",))
def gerir_produtos_add():
    sku = request.form["sku"]
    name = request.form["name"]
    description = request.form["description"] if request.form["description"] else None
    price = request.form["price"]
    ean = request.form["ean"] if request.form["ean"] else None
    error = ""

    if not sku or len(sku) > 25:
        error = "SKU inválido"
    elif not name or len(name) > 200:
        error = "Nome inválido"
    elif not price or (not re.fullmatch(float_regex, price) and not price.isnumeric()) or float(price) < 0:
        error = "Preço inválido"
    elif ean is not None and (not ean.isnumeric() or len(ean) != 13):
        error = "EAN inválido"

    if error:
        flash(error)
    else:
        with pool.connection() as conn:
            with conn.cursor() as cursor:
                cursor.execute("START TRANSACTION;")
                existing_sku = cursor.execute(
                    "SELECT sku FROM product WHERE sku = %(sku)s;", {"sku": sku}).fetchone()
                if existing_sku:
                    error = "O SKU já existe"
                elif ean != None:
                    existing_ean = cursor.execute(
                        "SELECT ean FROM product WHERE ean = %(ean)s;", {"ean": ean}).fetchone()
                    if existing_ean:
                        error = "O EAN já existe"

                if error:
                    flash(error)
                else:
                    cursor.execute(
                        """
                        INSERT INTO product VALUES
                        (%(sku)s, %(name)s, %(description)s, %(price)s, %(ean)s);
                        """,
                        {"sku": sku, "name": name, "description": description, "price": price, "ean": ean})
                cursor.execute("COMMIT;")

    return redirect("/gerir-produtos")


@app.route("/gerir-fornecedores", methods=("GET",))
def gerir_fornecedores():
    with pool.connection() as conn:
        with conn.cursor() as cursor:
            cursor.execute("SELECT TIN, name FROM supplier;")
            def parser(el): return {
                "id": el[0], "name": el[1] if el[1] != None else ""}
            suppliers = list(map(parser, cursor.fetchall()))
            psize = 5
            pcount = len(suppliers) // psize + 1 if len(suppliers) % psize != 0 else len(suppliers) // psize
    return render_template("gerir-fornecedores.html", suppliers=suppliers, pcount=pcount, psize=psize)


@app.route("/gerir-fornecedores/<tin>/delete", methods=("POST",))
def gerir_fornecedores_delete(tin):
    with pool.connection() as conn:
        with conn.cursor() as cursor:
            cursor.execute("START TRANSACTION;")
            cursor.execute(
                "DELETE FROM delivery WHERE tin = %(tin)s;", {"tin": tin})
            cursor.execute(
                "DELETE FROM supplier WHERE tin = %(tin)s;", {"tin": tin})
            cursor.execute("COMMIT;")
    return redirect("/gerir-fornecedores")


@app.route("/gerir-fornecedores/add", methods=("POST",))
def gerir_fornecedores_add():
    tin = request.form["tin"]
    name = request.form["name"] if request.form["name"] else None
    address = request.form["address"] if request.form["address"] else None
    sku = request.form["sku"] if request.form["sku"] else None
    error = ""

    if not tin or len(tin) > 20:
        error = "TIN inválido"
    elif name is not None and len(name) > 200:
        error = "Nome inválido"
    elif address is not None and (not re.fullmatch(address_regex, address) or len(address) > 255):
        error = "Morada inválida"
    elif sku is not None and len(sku) > 25:
        error = "SKU inválido"

    if error:
        flash(error)
    else:
        with pool.connection() as conn:
            with conn.cursor() as cursor:
                cursor.execute("START TRANSACTION;")
                existing_tin = cursor.execute(
                    "SELECT tin FROM supplier WHERE tin = %(tin)s;", {"tin": tin}).fetchone()
                if existing_tin:
                    error = "O TIN já existe"
                elif sku is not None:
                    existing_sku = cursor.execute(
                        "SELECT sku FROM product WHERE sku = %(sku)s;", {"sku": sku}).fetchone()
                    if not existing_sku:
                        error = "O SKU não existe"

                if error:
                    flash(error)
                else:
                    cursor.execute(
                        """
                        INSERT INTO supplier VALUES
                        (%(tin)s, %(name)s, %(address)s, %(sku)s, %(date)s);
                        """,
                        {"tin": tin, "name": name, "address": address, "sku": sku, "date": datetime.now().strftime("%Y-%m-%d")})
                cursor.execute("COMMIT;")
    return redirect("/gerir-fornecedores")


@app.route("/gerir-encomendas", methods=("GET",))
def gerir_encomendas():
    with pool.connection() as conn:
        with conn.cursor() as cursor:
            cursor.execute(
                "SELECT order_no FROM orders WHERE NOT order_no IN (SELECT order_no FROM pay);")
            orders = []
            for order_no in cursor.fetchall():
                order = {"id": order_no[0]}
                cursor.execute(
                    """
                    SELECT name, qty
                    FROM contains INNER JOIN product ON contains.sku = product.sku
                    WHERE order_no = %(order_no)s;
                    """,
                    {"order_no": order_no[0]})

                def parser(el): return {"name": el[0], "qty": el[1]}
                order["products"] = list(map(parser, cursor.fetchall()))
                orders.append(order)

            def parser(el): return {"id": el[0], "customer": el[1]}
            cursor.execute("SELECT order_no, cust_no FROM pay;")
            sales = list(map(parser, cursor.fetchall()))

            opsize = 5
            opcount = len(orders) // opsize + 1 if len(orders) % opsize != 0 else len(orders) // opsize

            spsize = 5
            spcount = len(sales) // spsize + 1 if len(sales) % spsize != 0 else len(sales) // spsize

    return render_template("gerir-encomendas.html", sales=sales, orders=orders, spcount=spcount, spsize=spsize, opcount=opcount, opsize=opsize)


@app.route("/gerir-encomendas/pay", methods=("POST",))
def gerir_encomenda_pay():
    cust_no = request.form["cust_no"]
    order_no = request.form["order_no"]
    error = ""

    if not cust_no or not cust_no.isnumeric():
        error = "Número de cliente inválido"
    elif not order_no or not order_no.isnumeric():
        error = "Número de encomenda inválido"

    if error:
        flash(error)
    else:
        with pool.connection() as conn:
            with conn.cursor() as cursor:
                cursor.execute("START TRANSACTION;")
                existing_cust_no = cursor.execute(
                    "SELECT cust_no FROM customer WHERE cust_no = %(cust_no)s AND NOT email LIKE '%%deleted';", {"cust_no": cust_no}).fetchone()
                if not existing_cust_no:
                    error = "O número de cliente não existe"
                existing_order_no = cursor.execute("SELECT order_no FROM orders WHERE order_no = %(order_no)s;", {
                                                   "order_no": order_no}).fetchone()
                if not existing_order_no:
                    error = "O número de encomenda não existe"
                ordered_by_client = cursor.execute("SELECT order_no FROM orders WHERE order_no = %(order_no)s AND cust_no = %(cust_no)s;", {
                                                   "order_no": order_no, "cust_no": cust_no}).fetchone()
                if not ordered_by_client:
                    error = "A encomenda não foi feita por este cliente"

                if error:
                    flash(error)
                else:
                    cursor.execute(
                        """
                        INSERT INTO pay VALUES
                        (%(order_no)s, %(cust_no)s);
                        """,
                        {"order_no": order_no, "cust_no": cust_no})
                cursor.execute("COMMIT;")

    return redirect("/gerir-encomendas")


@app.route("/realizar-encomenda", methods=("POST", "GET"))
def realizar_encomenda():
    if request.method == "POST":
        cust_no = request.form["cust_no"]
        products = []
        error = ""
        for key in request.form.keys():
            if key != "cust_no" and request.form[key] != "0":
                if not request.form[key].isnumeric() or int(request.form[key]) < 0:
                    error = "Quantidade inválida"
                    break
                products.append((key, request.form[key]))

        if (cust_no is not None and not cust_no.isnumeric()):
            error = "Número de cliente inválido"
        elif not products:
            error = "Nenhum produto selecionado"

        if error:
            flash(error)
        else:
            with pool.connection() as conn:
                with conn.cursor() as cursor:
                    cursor.execute("START TRANSACTION;")
                    existing_cust_no = cursor.execute(
                        "SELECT cust_no FROM customer WHERE cust_no = %(cust_no)s AND NOT email LIKE '%%deleted';", {"cust_no": cust_no}).fetchone()
                    if not existing_cust_no:
                        error = "O número de cliente não existe"
                    else:
                        for product in products:
                            existing_sku = cursor.execute("SELECT sku FROM product WHERE sku = %(sku)s;", {
                                                          "sku": product[0]}).fetchone()
                            if not existing_sku:
                                error = "Um dos produtos que encomendou já não existe"
                                break
                    if error:
                        flash(error)
                    else:
                        order_no = cursor.execute(
                            "SELECT MAX(order_no) FROM orders;").fetchone()[0]
                        if not order_no:
                            order_no = 1
                        else:
                            order_no += 1

                        cursor.execute(
                            """
                            INSERT INTO orders
                            VALUES (%(order_no)s, %(cust_no)s, %(date)s);
                            """,
                            {"order_no": order_no, "cust_no": cust_no, "date": datetime.now().strftime("%Y-%m-%d")})

                        for product in products:
                            cursor.execute("INSERT INTO contains VALUES (%(order_no)s, %(sku)s, %(quantity)s);", {
                                           "order_no": order_no, "sku": product[0], "quantity": product[1]})

                    cursor.execute("COMMIT;")
        return redirect("/realizar-encomenda")
    else:
        with pool.connection() as conn:
            with conn.cursor() as cursor:
                cursor.execute("SELECT sku, name FROM product;")
                def parser(el): return {"id": el[0], "name": el[1]}
                products = list(map(parser, cursor.fetchall()))
        return render_template("realizar-encomenda.html", products=products)


if __name__ == "__main__":
    app.run()
