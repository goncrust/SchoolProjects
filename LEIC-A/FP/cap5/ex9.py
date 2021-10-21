
def num_occ_lista(lista, num):

    soma = 0
    for n in lista:
        if isinstance(n, list):
            soma += num_occ_lista(n, num)
        elif n == num:
            soma += 1

    return soma
        
