
def divide(ficheiro, n):

    with open(ficheiro, 'r') as f:
        linhas = f.readlines()

    f1 = ficheiro + '0'
    f2 = ficheiro + '1'

    f1_o = open(f1, 'w')
    f2_o = open(f2, 'w')
    for l in linhas:
        if '\n' not in l[:20]:
            f1_o.write(l[:20] + '\n')
        else:
            f1_o.write(l[:20])

        if '\n' not in l[20:]:
            f2_o.write('\n')
        else:
            f2_o.write(l[20:])

    f1_o.close()
    f2_o.close()
            
