
def implode(digs):

    if not isinstance(digs, tuple):
        raise ValueError("implode: argumento nao tuplo")
    
    res = 0
    for i in range(len(digs)):

        if not isinstance(digs[i], int):
            raise ValueError("implode: elemento nao inteiro")

        res += digs[i] * (10 ** (len(digs) - i - 1))

    return res

