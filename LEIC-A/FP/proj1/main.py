
def corrigir_palavra(palavra):

    if not isinstance(palavra, str):
        raise ValueError("corrigir_palavra: parametro nao eh uma string")

    caso_encontrado = True
    while caso_encontrado:
        
        caso_encontrado = False

        for i in range(len(palavra) - 1):
            # A != a
            if palavra[i] != palavra[i+1]: 
                # a == a
                if palavra[i].lower() == palavra[i+1].lower():
                    palavra = palavra[:i] + palavra[i+2:]
                    caso_encontrado = True
                    break
                
    return palavra

print(corrigir_palavra(input()))
