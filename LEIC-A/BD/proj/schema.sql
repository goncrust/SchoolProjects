CREATE TABLE Customer (
    cust_no SERIAL NOT NULL,
    "name" VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phone NUMERIC(9, 0),
    "address" VARCHAR(255),
    PRIMARY KEY(cust_no)
);

-- (IC-2) Any order_no in Order must exist in the contains relation
CREATE TABLE "Order" (
    order_no SERIAL NOT NULL,
    "date" DATE,
    cust_no INT NOT NULL,
    PRIMARY KEY(order_no),
    FOREIGN KEY(cust_no) REFERENCES Customer(cust_no)
);

-- (IC-1) Customers can only pay for the Sale of an Order they have placed themselves ((order_no, payer_no) in (Order.order_no, Order.cust_no))
CREATE TABLE Sale (
    order_no INT NOT NULL,
    payer_no INT,
    PRIMARY KEY(order_no),
    FOREIGN KEY(order_no) REFERENCES "Order"(order_no)
        ON DELETE CASCADE,
    FOREIGN KEY(payer_no) REFERENCES Customer(cust_no)
);

-- (IC-3) Any sku in Product must exist in the supply-contract relation
CREATE TABLE Product (
    sku VARCHAR(255) NOT NULL,
    "name" VARCHAR(255),
    "description" VARCHAR(255),
    price INT,
    CHECK (price > 0),
    PRIMARY KEY(sku)
);

CREATE TABLE "EAN Product" (
    sku VARCHAR(255) NOT NULL,
    ean NUMERIC(13, 0),
    PRIMARY KEY(sku),
    FOREIGN KEY(sku) REFERENCES Product(sku)
        ON DELETE CASCADE
);

CREATE TABLE contains (
    order_no INT NOT NULL,
    sku VARCHAR(255) NOT NULL,
    qty INT,
    PRIMARY KEY(order_no, sku),
    FOREIGN KEY(order_no) REFERENCES "Order"(order_no),
    FOREIGN KEY(sku) REFERENCES Product(sku)
);

-- (IC-4) Any TIN in Supplier must exist in the supply-contract relation
CREATE TABLE Supplier (
    TIN NUMERIC(9, 0) NOT NULL,
    "name" VARCHAR(255),
    "address" VARCHAR(255),
    PRIMARY KEY(TIN)
);

CREATE TABLE "supply-contract" (
    TIN NUMERIC(9, 0) NOT NULL,
    sku VARCHAR(255) NOT NULL,
    "date" DATE,
    PRIMARY KEY(TIN),
    FOREIGN KEY(TIN) REFERENCES Supplier(TIN),
    FOREIGN KEY(sku) REFERENCES Product(sku)
);

-- (IC-5) Any ssn in Employee must exist in the works relation
CREATE TABLE Employee (
    ssn NUMERIC(11, 0) NOT NULL,
    TIN NUMERIC(9, 0),
    bdate DATE,
    "name" VARCHAR(255),
    PRIMARY KEY(ssn)
);

CREATE TABLE process (
    order_no INT NOT NULL,
    ssn NUMERIC(11, 0) NOT NULL,
    PRIMARY KEY(order_no, ssn),
    FOREIGN KEY(order_no) REFERENCES "Order"(order_no),
    FOREIGN KEY(ssn) REFERENCES Employee(ssn)
);

CREATE TABLE Department (
    "name" VARCHAR(255) NOT NULL,
    PRIMARY KEY("name")
);

CREATE TABLE Workplace (
    "address" VARCHAR(255) NOT NULL,
    lat NUMERIC(8, 6),
    "long" NUMERIC(9, 6),
    UNIQUE(lat, "long"),
    PRIMARY KEY("address")
);

CREATE TABLE Office (
    "address" VARCHAR(255) NOT NULL,
    PRIMARY KEY("address"),
    FOREIGN KEY("address") REFERENCES Workplace("address")
        ON DELETE CASCADE
);

CREATE TABLE Warehouse (
    "address" VARCHAR(255) NOT NULL,
    PRIMARY KEY("address"),
    FOREIGN KEY("address") REFERENCES Workplace("address")
        ON DELETE CASCADE
);

CREATE TABLE works (
    ssn NUMERIC(11, 0) NOT NULL,
    department VARCHAR(255) NOT NULL,
    "address" VARCHAR(255) NOT NULL,
    PRIMARY KEY(ssn, department, "address"),
    FOREIGN KEY(ssn) REFERENCES Employee(ssn),
    FOREIGN KEY(department) REFERENCES Department("name"),
    FOREIGN KEY("address") REFERENCES Workplace("address")
);

CREATE TABLE delivery (
    "address" VARCHAR(255) NOT NULL,
    TIN NUMERIC(9, 0) NOT NULL,
    PRIMARY KEY("address", TIN),
    FOREIGN KEY("address") REFERENCES Warehouse("address"),
    FOREIGN KEY(TIN) REFERENCES "supply-contract"(TIN)
);
