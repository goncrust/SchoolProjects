import ex2
import ex3for
import ex4

def algarismos_pares(num):

    if not isinstance(num, int):
        raise ValueError("algarismos_pares: argumento nao inteiro")

    sep = ex2.explode(num)
    pares = ex4.filtra_pares(sep)
    res = ex3for.implode(pares)

    return res

