
def numero_digitos(n):

    res = 1 
    while n > 9:
        res += 1
        n //= 10

    return res
