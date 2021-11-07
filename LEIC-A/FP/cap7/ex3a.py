
def numero_digitos(n):

    def aux(n):
        if n == 0:
            return 0
        else:
            return 1 + aux(n//10)

    if not isinstance(n, int):
        raise ValueError('argumento invalido')

    return aux(n)

