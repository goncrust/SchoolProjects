
def concatena(lista_fich, ficheiro):
    
    linhas = []
    for f in lista_fich:
        with open(f, 'r') as fich:
            linhas += fich.readlines() 

    with open(ficheiro, 'w') as fich:
        fich.writelines(linhas)

