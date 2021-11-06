
def procura(palavra, ficheiro):

    with open(ficheiro, 'r') as f:

        linha = f.readline()
        while linha != '':
            if palavra in linha:
                print(linha[:-1])

            linha = f.readline()

