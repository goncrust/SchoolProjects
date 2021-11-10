
def numero_digitos(n):

    def aux(n):
        if n < 10:
            return 1
        else:
            return 1 + aux(n//10)

    if not isinstance(n, int):
        raise ValueError('argumento invalido')

    return aux(n)

