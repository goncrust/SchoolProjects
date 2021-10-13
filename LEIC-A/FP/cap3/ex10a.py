
def dividir_algarismos(n):
    
    a = n % 10
    n //= 10
    b = n % 10
    n //= 10
    c = n % 10

    return c, b, a

def juntar_algarismos(a, b, c):

    n = a
    n *= 10
    n += b
    n *= 10
    n += c

    return n

def inverter_algarismos(n):

    a, b, c = dividir_algarismos(n)
    n = juntar_algarismos(c, b, a)

    return n

def misterio(n):
    ni = 0
    ns = 0
    nsi = 0

    if n < 100 or n > 999:
        print("Condicoes nao verificadas")
        return

    a, b, c = dividir_algarismos(n)

    if a - c == 0:
        print("Condicoes nao verificadas")
        return

    ni = juntar_algarismos(c, b, a)

    if n > ni:
        ns = n - ni
    else:
        ns = ni - n

    nsi = inverter_algarismos(ns)

    res = ns + nsi

    return res

