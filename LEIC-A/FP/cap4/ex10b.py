
def descodifica(texto):

    i_pares = len(texto) // 2
    if len(texto) % 2 != 0:
        i_pares += 1

    pares = texto[:i_pares]
    impares = texto[i_pares:]

    res = ""
    for i in range(len(pares)):
        par = pares[i]
        if i >= len(impares):
            impar = ""
        else:
            impar = impares[i]

        res += par + impar

    return res
