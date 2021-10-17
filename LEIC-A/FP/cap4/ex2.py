
def explode(num):

    if not isinstance(num, int):
        raise ValueError("explode: argumento nao inteiro")

    res = ()
    for i in str(num):
        res += (int(i),)

    return res
