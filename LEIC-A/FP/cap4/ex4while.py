

def implode(digs):

    if not isinstance(digs, tuple):
        raise ValueError("implode: argumento nao tuplo")
    
    res = 0
    i = 0
    digs_l = len(digs)
    while i < digs_l:

        if not isinstance(digs[i], int):
            raise ValueError("implode: elemento nao inteiro")

        res += digs[i] * (10 ** (len(digs) - i - 1))

        i += 1

    return res

