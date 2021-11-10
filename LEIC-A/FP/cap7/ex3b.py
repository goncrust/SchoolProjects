
def numero_digitos(n):

    def aux(n, res):
        if n < 10:
            return res
        else:
            return aux(n//10, res + 1)

    if not isinstance(n, int):
        raise ValueError('argumentos invalidos')

    return aux(n, 1)

