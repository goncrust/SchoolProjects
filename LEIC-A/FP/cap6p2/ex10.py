from functools import reduce
import ex8


def apenas_digitos_impares(n):
    return reduce(lambda x, y: x*10 + y, filter(lambda x: x % 2 != 0, ex8.lista_digitos(n)))

