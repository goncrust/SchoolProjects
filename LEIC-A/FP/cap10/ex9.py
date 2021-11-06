
def separa(ficheiro, carater, n):

    with open(ficheiro, 'r') as f:
        linhas = f.readlines()

    linhas_f0 = []
    linhas_f1 = []
    for l in range(len(linhas)):
        linhas_f0 += ['']
        linhas_f1 += ['']

        ocorrencias_carater = 0
        triggered = False
        for c in linhas[l]:
            if c == carater:
                if triggered:
                    triggered = False

                ocorrencias_carater += 1
                if ocorrencias_carater == n:
                    triggered = True

            if triggered:
                linhas_f0[l] += c
            else:
                linhas_f1[l] +=c

    f0 = ficheiro + '0'
    f1 = ficheiro + '1'
    with open(f0, 'w') as f:
        for l in linhas_f0:
            if '\n' not in l:
                l += '\n'
            f.write(l)

    with open(f1, 'w') as f:
        for l in linhas_f1:
            if '\n' not in l:
                l += '\n'
            f.write(l)

