import ex7a

def duplicar(q, j):
    
    dinheiro = q
    res = 0

    while dinheiro < q * 2:
        res += 1
        dinheiro = ex7a.valor(q, j, res)

    return res


