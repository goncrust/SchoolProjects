"""FP - Primeiro Projeto (Buggy Data Base)


"""

# 1 - Correção da documentação

def corrigir_palavra(palavra):
    """Corrige surto de letras

    Recebe uma string e devolve uma string com o surto de letras corrigido.
    (cad. carateres --> cad. carateres)
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

    Recebe duas strings e devolve True se sao constituidas pelas mesmas letras,
    ignorando diferenças entre maiusculas e minusculas.
    (cad. carateres x cad.carateres --> booleano)
    """


    def procurar_e_destruir(palavra, carater):
        """Remove a primeira ocorrencia de um carater numa string

        Recebe uma string e uma string apenas com um carater e, 
        utilizando o algoritmo de procura sequencial, devolve uma
        string com a primeira ocorrencia do carater eliminada, caso ele exista.
        No caso de nao existir, é devolvida a string original.
        (cad. carateres x cad. carateres --> cad. carateres)
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
    (cad. carateres --> cad. cateres)
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
    (cad. carateres x inteiro --> inteiro)
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
    (cad. carateres x inteiro --> inteiro)
    """

    for d in direcoes:
        posicao = obter_posicao(d, posicao)

    return posicao

def obter_pin(movimentos):
    """Calcula o PIN codificado de acordo com os movimentos

    Recebe um tuplo que contem 4 a 10 sequencias de movimentos e devolve
    o tuplo de inteiros que contem o PIN codificado de acordo com os movimentos.
    (tuplo --> tuplo)
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

# 3 - Verificação de dados

def eh_entrada(argumento):
    """Verifica se argumento é entrada da BDB

    Recebe um argumento de qualquer tipo e devolve True se este corresponde
    ao formato de uma entrada da BDB, caso contrário devolve False.
    (universal --> booleano)
    """

    def eh_alpha_lower(s):
        """Verifica se todos os carateres da string pertencem ao alfabeto e sao minusculos

        Recebe uma string devolve True se todos os seus carateres sao letras do alfabeto,
        e minusculas. Caso contrário, devolve False.
        (cad. carateres --> booleano)
        """

        if not s.isalpha() or not s.islower():
            return False

        return True


    def eh_cifra(cifra):
        """Verifica se o argumento corresponde a uma cifra de uma entrada da BDB

        Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde ao formato
        de uma cifra de uma possivel entrada da BDB. 
        Caso contrário, devolve False.
        (universal --> booleano)
        """

        if isinstance(cifra, str):

            cifra = cifra.split('-')
            sucesso = True

            for s in cifra:
                sucesso = eh_alpha_lower(s) 

            return sucesso

        return False

    def eh_sequencia_controlo(sc):
        """Verifica se o argumento corresponde a uma sequencia de controlo de uma entrada da BDB

        Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde ao formato
        de uma sequencia de controlo de uma possivel entrada da BDB. 
        Caso contrário, devolve False.
        (universal --> booleano)
        """

        if isinstance(sc, str) and len(sc) == 7:
            if sc[0] == '[' and sc[6] == ']':
                return eh_alpha_lower(sc[1:6])

        return False

    def eh_sequencia_seguranca(ss):
        """Verifica se o argumento corresponde a uma sequencia de seguranca de uma entrada da BDB

        Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde ao formato
        de uma sequencia de seguranca de uma possivel entrada da BDB. 
        Caso contrário, devolve False.
        (universal --> booleano)
        """

        if isinstance(ss, tuple) and len(ss) >= 2:
            for n in ss:
                if not isinstance(n, int) or n < 0:
                    return False

            return True

        return False


    # Tipo/tamanho do argumento
    if isinstance(argumento, tuple) and len(argumento) == 3:

        if eh_cifra(argumento[0]):
            if eh_sequencia_controlo(argumento[1]):
                return eh_sequencia_seguranca(argumento[2])

    return False

def validar_cifra(cifra, sc):
    """Verifica se a sequencia de controlo é coerente com a cifra

    Recebe uma cifra (string) e uma sequencia de controlo (string) e devolve True
    se e só se a sequencia de controlo é coerente com a cifra, caso contrario devolve False.
    Para a sequencia de controlo ser coerente com a cifra, a mesma deve ser formada pelas
    cinco letras mais comuns na cifra, por ordem inversa de ocorrencias, com empates decididos
    por ordem alfabética.
    (cad. carateres x cad. carateres --> booleano)
    """

    def primeiro_no_alfabeto(c1, c2):
        """Verifica se c1 aparece primeiro no alfabeto em relacao a c2

        Devolve True se c1 (string com apenas um carater) aparece primeiro no alfabeto 
        em relacao a c2 (string com apenas um carater). Devolve False caso contrário.
        (cad. carateres x cad. carateres --> booleano)
        """

        alfabeto = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

        for l in alfabeto:
            if l == c1:
                return True
            elif l == c2:
                return False


    def sort_ocorrencias(lista):
        """Ordena a lista de ocorrencias de letras

        Ordena a lista (argumento) que contem listas que represetam a ocorrencia de letras 
        (i.e. [['a', 2], ['b', 3]]) por ordem inversa de ocorrencias de cada letra. 
        Caso duas letras tenham o mesmo numero de ocorrencias, o empate é decidido por
        ordem alfabética.
        (lista --> {})
        """

        alteracao = True
        
        for i in range(len(lista) - 1, 0, -1):

            alteracao = False

            for j in range(1, i + 1):
                if lista[j][1] > lista[j - 1][1]:
                    lista[j], lista[j - 1] = lista[j - 1], lista[j]

                    alteracao = True
                elif lista[j][1] == lista[j - 1][1]:
                    # Se lista[j][0] primeiro no alfabeto em relacao a lista[j-1][0], entao troca
                    if primeiro_no_alfabeto(lista[j][0], lista[j - 1][0]):
                        lista[j], lista[j - 1] = lista[j - 1], lista[j]

                        alteracao = True

            if not alteracao:
                break


    # Contar ocorrencias
    ocorrencias = {}

    for c in cifra:
        if c == '-':
            continue

        if c not in ocorrencias:
            ocorrencias[c] = 0

        ocorrencias[c] += 1

    # Colocar por ordem
    ocorrencias = list(ocorrencias.items())
    sort_ocorrencias(ocorrencias)

    # Comparar com sequencia de controlo
    string_ocorrencias = "["
    for i in range(5):
        string_ocorrencias += ocorrencias[i][0]

    string_ocorrencias += "]"

    return string_ocorrencias == sc

def filtrar_bdb(lista):
    """Determina as entradas da BDB em que a sequencia de controlo não é coerente com a cifra

    Recebe uma lista com uma ou mais entradas da BDB e devolve uma lista que contém as entradas
    em que a sequencia de controlo não é coerente com a cifra, na mesma ordem da lista original.
    (lista --> lista)
    """

    if not isinstance(lista, list) or len(lista) < 1:
        raise ValueError('filtrar_bdb: argumento invalido')

    lista_incoerentes = []
    for i in range(len(lista)):

        if not eh_entrada(lista[i]):
            raise ValueError('filtrar_bdb: argumento invalido')

        if not validar_cifra(lista[i][0], lista[i][1]):
            lista_incoerentes.append(lista[i])

    return lista_incoerentes

# 4 - Desencriptação de dados
