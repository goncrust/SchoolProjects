import ex4a
import ex4b
import ex4c


def soma_quadrados_impares(lst):
    return ex4c.acumula(ex4b.transforma(ex4a.filtra(lst, lambda x: x % 2 != 0), lambda x: x*x), lambda x, y: x + y)

