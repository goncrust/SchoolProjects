"""FP - Primeiro Projeto (Buggy Data Base)


"""

# 1 - Correcao da documentacao

def corrigir_palavra(palavra):
    """Corrige surto de letras

    Recebe uma string e retorna uma string com o surto de letras corrigido.
    (cad. carateres -> cad. carateres)
    """

    caso_encontrado = True
    while caso_encontrado:
        
        caso_encontrado = False

        for i in range(len(palavra) - 1):
            # Verifica se as letras sao diferentes (ex: A != a)
            if palavra[i] != palavra[i+1]: 
                # Mas se ambas em minusculo sao iguais (ex: a == a)
                if palavra[i].lower() == palavra[i+1].lower():
                    palavra = palavra[:i] + palavra[i+2:]
                    caso_encontrado = True
                    break
                
    return palavra

def eh_anagrama(palavra1, palavra2):
    """Deteta se palavra é anagrama da outra

    Recebe duas strings e retorna True se sao constituidas pelas mesmas letras,
    ignorando diferenças entre maiusculas e minusculas.
    (cad. carateres x cad.carateres -> booleano)
    """


    def procurar_e_destruir(palavra, carater):
        """Remove a primeira ocorrencia de um carater numa string

        Recebe uma string e uma string apenas com um carater e, 
        utilizando o algoritmo de procura sequencial, retorna uma
        string com a primeira ocorrencia do carater eliminada, caso ele exista.
        No caso de nao existir, é retornada a string original.
        (cad. carateres x cad. carateres -> cad. carateres)
        """

        for i in range(len(palavra)):
            if palavra[i] == carater:
                return palavra[:i] + palavra[i+1:]

        return palavra

    if len(palavra1) != len(palavra2) or palavra1 == palavra2:
        return False

    palavra1 = palavra1.lower()
    palavra2 = palavra2.lower()

    for c in palavra1:
        temp = palavra2
        palavra2 = procurar_e_destruir(palavra2, c)

        # Caso a palavra2 nao tenha sido alterada, entao o carater nao existia na mesma
        if palavra2 == temp:
            return False

    return True

def corrigir_doc(texto):
    """Corrige texto com erros da documentacao da BDB

    Recebe o texto da documentacao da BDB com erros (string), 
    corrige as palavras com surto de letras e remove palavras sem sentido 
    que sao anagramas das palavras anteriores. Retorna o texto corrigido (string).
    (cad. carateres -> cad. cateres)
    """

    if not isinstance(texto, str):
        raise ValueError('corrigir_doc: argumento invalido')

    palavras = texto.split(' ')

    # Caso existam palavras separadas por mais do que um espaço ou a string esteja vazia, 
    # a lista palavras ficará com uma string vazia
    if '' in palavras:
        raise ValueError('corrigir_doc: argumento invalido')

    # Corrigir surtos de letras
    for i in range(len(palavras)):
        palavras[i] = corrigir_palavra(palavras[i])

    # Remover anagramas
    i = 0
    while i < len(palavras):

        # Verificar que todas as palavras sao constituidas apenas por letras do alfabeto
        if not palavras[i].isalpha():
            raise ValueError('corrigir_doc: argumento invalido')

        for j in range(len(palavras[i+1:])):

            indice_concreto_j = i + j + 1

            if eh_anagrama(palavras[i], palavras[indice_concreto_j]):
                del palavras[indice_concreto_j]
                break

        i += 1

    # Voltar a juntar texto
    texto = ''
    for p in palavras:
        texto += p + ' ' 
        
    # Retirar ultimo espaço
    texto = texto[:len(texto) - 1]

    return texto

# 2 - Descoberta do PIN

def obter_posicao(direcao, posicao):
    """Calcula o inteiro que corresponde à nova posicao, apos um movimento

    Recebe uma string apenas com um carater, que é a direcao,
    e um inteiro, que é a posicao atual. Retorna um inteiro que corresponde 
    à nova posicao.
    (cad. carateres x inteiro -> inteiro)
    """

    # Matriz 3x3 que corrosponde aos digitos do painel 
    painel = ((1, 2, 3), (4, 5, 6), (7, 8, 9))

    linha = 0
    coluna = 0

    # Obter linha do ponto de partida
    if posicao < 4:
        linha = 0
    elif posicao < 7:
        linha = 1
    elif posicao < 10:
        linha = 2
        
    # Obter coluna do ponto de partida
    coluna = posicao - linha * 3 - 1

    # Calcular movimento
    if direcao == 'C':
        if linha != 0:
            linha -= 1
    elif direcao == 'B':
        if linha != 2:
            linha += 1
    elif direcao == 'E':
        if coluna != 0:
            coluna -= 1
    elif direcao == 'D':
        if coluna != 2:
            coluna += 1

    return painel[linha][coluna]

def obter_digito(direcoes, posicao):
    """Calcula o inteiro que corresponde à nova posicao, apos um ou mais movimentos

    Recebe uma string, que contem as direcoes, e um inteiro, que é a posicao atual. 
    Retorna um inteiro que corresponde à nova posicao.
    (cad. carateres x inteiro -> inteiro)
    """

    for d in direcoes:
        posicao = obter_posicao(d, posicao)

    return posicao

def obter_pin(movimentos):
    """Calcula o PIN codificado de acordo com os movimentos

    Recebe um tuplo que contem 4 a 10 sequencias de movimentos e retorna
    o tuplo de inteiros que contem o PIN codificado de acordo com os movimentos.
    (tuplo -> tuplo)
    """

    if not isinstance(movimentos, tuple) or not 4 <= len(movimentos) <= 10:
        raise ValueError('obter_pin: argumento invalido')

    posicao = 5
    res = ()

    for ms in movimentos:

        # Verificar a validade da sequencia de movimentos
        if not isinstance(ms, str) or ms == '':
            raise ValueError('obter_pin: argumento invalido')

        # Verificar validade das direcoes
        for m in ms:
            if m != 'C' and m != 'B' and m != 'E' and m != 'D':
                raise ValueError('obter_pin: argumento invalido')

        posicao = obter_digito(ms, posicao)
        res += (posicao,)

    return res


