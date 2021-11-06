
def conta_vogais(ficheiro):
    vogais_min = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

    res = {}
    with open(ficheiro, 'r') as f:
        linha = f.readline()

        while linha != '':

            for c in linha:
                if c in vogais_min:
                    if c not in res:
                        res[c] = 0

                    res[c] += 1

            linha = f.readline()

    return res
