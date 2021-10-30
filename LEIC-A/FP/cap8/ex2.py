
def agrupa_por_chave(lista):

    res = {}
    for l in lista:
        if l[0] not in res: 
            res[l[0]] = []

        res[l[0]].append(l[1])

    return res
