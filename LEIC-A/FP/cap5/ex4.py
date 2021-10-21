
def elemento_matriz(matriz, linha, coluna):

    if linha >= len(matriz):
        raise ValueError('indice invalido, linha {}'.format(linha))
    elif coluna >= len(matriz[linha]):
        raise ValueError('indice invalido, coluna {}'.format(coluna))


    return matriz[linha][coluna]
