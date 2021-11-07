
def numero_digitos(n):

    res = 0
    while n != 0:
        res += 1
        n //= 10

    return res
