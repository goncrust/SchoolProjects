
def filtra_pares(digs):

    if not isinstance(digs, tuple):
        raise ValueError("filtra_pares: argumento nao tuplo")

    res = ()
    for d in digs:
        if not isinstance(d, int):
            raise ValueError("filtra_pares: elemento nao inteiro")

        if d % 2 == 0:
            res += (d,)

    return res

