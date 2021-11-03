import ex5b

def produto_escalar(v1, v2):
    a = ex5b.abcissa(v1) * ex5b.abcissa(v2)
    o = ex5b.ordenada(v1) * ex5b.ordenada(v2)

    return a + o
