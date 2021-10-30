
def escreve_esparsa(d):

    colunas = 0
    linhas = 0
    for k in d:
        if k[0] > linhas:
            linhas = k[0]

        if k[1] > colunas:
            colunas = k[1]

    for l in range(linhas + 1):
        for c in range(colunas + 1):
            if (l, c) in d:
                print(str(d[(l, c)]), end='\t')
            else:
                print('0', end='\t')

        print()
