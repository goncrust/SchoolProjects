
def escreve_invertido(f1, f2):

    with open(f1, 'r') as f:
        linhas = f.readlines()

    with open(f2, 'w') as f:
        for i in range(len(linhas)-1, -1, -1):
            f.write(linhas[i])

