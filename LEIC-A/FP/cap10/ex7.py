
def ordena_ficheiro(ficheiro):

    with open(ficheiro, 'r') as f:
        linhas = f.readlines()

    caso = True
    for i in range(len(linhas) - 2, -1, -1):
        caso = False
        for j in range(0, i + 1):
            if len(linhas[j]) > len(linhas[j+1]):
                linhas[j], linhas[j+1] = linhas[j+1], linhas[j]
                caso = True

        if not caso:
            break

    for l in linhas:
        print(l[:-1])
