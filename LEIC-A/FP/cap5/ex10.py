import random

def new_random(lista, max):
    
    num = int(random.random() * max) + 1

    while num in lista:
        num = int(random.random() * max) + 1

    return num

def insere_ord(lista, num):
    
    if len(lista) == 0:
        lista.append(num)
        return
        
    for i in range(len(lista)):
        if lista[i] > num:
            lista.insert(i, num)
            return

    lista.append(num)



def euro_milhoes():
    res = [[], []]

    for i in range(5):
        insere_ord(res[0], new_random(res, 50))

    for e in range(2):
        insere_ord(res[1], new_random(res, 12))

    return res


