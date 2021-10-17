
def codifica(texto):

    pares = ""
    impares = ""
    for i in range(len(texto)):
        if i % 2 == 0:
            pares += texto[i]
        else:
            impares += texto[i]

    res = pares + impares
    return res

