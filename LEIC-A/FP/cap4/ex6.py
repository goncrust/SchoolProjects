import ex2

def num_para_seq_cod(num):

    if not isinstance(num, int):
        raise ValueError("num_para_seq_cod: argumento nao inteiro")

    sep = ex2.explode(num)

    for i in range(len(sep)):
        subs = 0
        if sep[i] % 2 == 0:
            if sep[i] == 8:
                subs = 0
            else:
                subs = sep[i] + 2
        else:
            if sep[i] == 1:
                subs = 9
            else:
                subs = sep[i] - 2

        sep = sep[:i] + (subs,) + sep[i+1:]

    return sep




