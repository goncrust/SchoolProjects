
def soma_cumulativa(lista):

    soma = 0
    for i in range(len(lista)):
        soma += lista[i]
        lista[i] = soma

    return lista
