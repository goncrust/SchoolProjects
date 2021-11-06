
def corta(entrada, saida, n):

    conteudo_entrada = '' 
    with open(entrada, 'r') as f:
        conteudo_entrada = f.read()

    with open(saida, 'w') as f:
        f.write(conteudo_entrada[:n])

