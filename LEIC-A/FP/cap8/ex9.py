
def mais_antigo(bib):

    ano_mais_antigo = 3000
    titulo_mais_antigo = ''
    for e in bib:
        if e['ano'] < ano_mais_antigo:
            ano_mais_antigo = e['ano']
            titulo_mais_antigo = e['titulo']

    return titulo_mais_antigo
