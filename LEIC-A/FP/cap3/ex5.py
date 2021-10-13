
def bissexto(ano):
    res = (ano % 4 == 0 and ano % 100 != 0) or ano % 400 == 0

    return res
