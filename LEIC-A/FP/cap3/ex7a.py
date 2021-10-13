
def valor(q, j, n):

    if q <= 0:
        raise ValueError("A quantia nao pode ser menor ou igual a 0")
    elif j <= 0 or j >= 1:
        raise ValueError("A taxa de juro deve ser um numero entre 0 e 1, exclusive")
    elif n < 0:
        raise ValueError("O numero de anos nao pode ser negativo")

    res = q * (1 + j) ** n

    return res

