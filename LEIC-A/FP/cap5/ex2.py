
def remove_multiplos(lista, num):

    for i in range(len(lista) - 1, -1, -1):
        if (lista[i] % num == 0):
            del lista[i]

    return lista
