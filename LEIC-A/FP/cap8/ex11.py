
# 8 linhas x 8 colunas
#   1 a 8      A a H
# canto inferior esquerdo (1, A)
# peças-> cores: brancas e pretas | tipos: rei, rainha, bipo, torre, cavalo, peao)
# dict: (l, c): (cor, t)

def check_vertical(j, cor, coords):
    
    c = coords[1]
    res = []

    for l in range(coords[0] + 1, 9):
        if (l, c) in j:
            if j[(l, c)][0] != cor:
                res.append([j[l, c][1], j[l, c][0], (l, c)])
            break

    for l in range(coords[0] - 1, 0, -1):
        if (l, c) in j:
            if j[(l, c)][0] != cor:
                res.append([j[l, c][1], j[l, c][0], (l, c)])
            break

    return res

def check_horizontal(j, cor, coords):
    
    l = coords[0]
    res = []

    colunas = ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')
    c_i = 0
    for c in colunas:
        if c == coords[1]:
            break
        c_i += 1

    for c in range(c_i + 1, 8):
        if (l, colunas[c]) in j:
            if j[(l, colunas[c])][0] != cor:
                res.append([j[l, colunas[c]][1], j[l, colunas[c]][0], (l, colunas[c])])
            break

    for c in range(c_i - 1, -1, -1):
        if (l, colunas[c]) in j:
            if j[(l, colunas[c])][0] != cor:
                res.append([j[l, colunas[c]][1], j[l, colunas[c]][0], (l, colunas[c])])
            break

    return res

def check_diagonal(j, cor, coords):

    l = coords[0]
    res = []

    colunas = ('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')
    c_i = 0
    for c in colunas:
        if c == coords[1]:
            break
        c_i += 1

    c = c_i
    original_c = c

    l += 1
    c += 1
    while not l > 8 and not c > 7:
        if (l, colunas[c]) in j:
            if j[(l, colunas[c])][0] != cor:
                res.append([j[l, colunas[c]][1], j[l, colunas[c]][0], (l, colunas[c])])
            break

        l += 1
        c += 1

    l = coords[0] + 1
    c = original_c - 1
    while not l > 8 and not c < 0:
        if (l, colunas[c]) in j:
            if j[(l, colunas[c])][0] != cor:
                res.append([j[l, colunas[c]][1], j[l, colunas[c]][0], (l, colunas[c])])
            break

        l += 1
        c -= 1

    l = coords[0] - 1
    c = original_c + 1
    while not l < 1 and not c > 7:
        if (l, colunas[c]) in j:
            if j[(l, colunas[c])][0] != cor:
                res.append([j[l, colunas[c]][1], j[l, colunas[c]][0], (l, colunas[c])])
            break

        l -= 1
        c += 1

    l = coords[0] - 1
    c = original_c - 1
    while not l < 1 and not c < 0 :
        if (l, colunas[c]) in j:
            if j[(l, colunas[c])][0] != cor:
                res.append([j[l, colunas[c]][1], j[l, colunas[c]][0], (l, colunas[c])])
            break

        l -= 1
        c -= 1

    return res


def ataques_rainhas(j):
        
    rainha_branca = 0
    rainha_preta = 0

    res = []

    for p in j:
        if j[p] == ('branca', 'rainha'):
            rainha_branca = p
        elif j[p] == ('preta', 'rainha'):
            rainha_preta = p

    # Branca
    if rainha_branca != 0:
        res.extend(check_vertical(j, 'branca', rainha_branca))
        res.extend(check_horizontal(j, 'branca', rainha_branca))
        res.extend(check_diagonal(j, 'branca', rainha_branca))

    # Preta
    if rainha_preta != 0:
        res.extend(check_vertical(j, 'preta', rainha_preta))
        res.extend(check_horizontal(j, 'preta', rainha_preta))
        res.extend(check_diagonal(j, 'preta', rainha_preta))

    return res


j = {(1, 'H'): ('branca', 'torre'), (2, 'F'): ('branca', 'peao'), \
     (2, 'G'): ('branca', 'rei'), (6, 'F'): ('branca', 'bispo'), \
     (5, 'C'): ('branca', 'rainha'), (6, 'G'): ('preta', 'peao'), \
     (7, 'F'): ('preta', 'peao'), (8, 'F'): ('preta', 'torre'), \
     (8, 'G'): ('preta', 'rei'), (2, 'C'): ('preta', 'peao')}

print(ataques_rainhas(j))
