
def distribui(baralho):

    cartas_distribuir = len(baralho) // 4

    res = []
    for i in range(4):
        res.append([])
        for j in range(cartas_distribuir):
            res[i].append(baralho[j + 4*i])

    return res
