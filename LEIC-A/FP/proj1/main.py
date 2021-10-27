"""FP - Primeiro Projeto (Buggy Data Base)


"""

# Funções adicionais (que são usadas em mais do que uma função)


def eh_alpha_lower(s):
    """Verifica se todos os carateres da string pertencem ao alfabeto e sao minusculos

    Recebe uma string e devolve True se todos os seus carateres são letras do alfabeto,
    e minusculas. Caso contrário, devolve False.
    (cad. carateres --> booleano)
    """

    if not s.isalpha() or not s.islower():
        return False

    return True


def primeiro_no_alfabeto(c1, c2):
    """Verifica se c1 aparece primeiro no alfabeto em relação a c2

    Devolve True se c1 (string com apenas um carater) aparece primeiro no alfabeto 
    em relação a c2 (string com apenas um carater). Devolve False caso contrário.
    (cad. carateres x cad. carateres --> booleano)
    """

    alfabeto = ('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')

    for l in alfabeto:
        if l == c1:
            return True
        elif l == c2:
            return False

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

    Recebe duas strings e devolve True se são constituidas pelas mesmas letras,
    ignorando diferenças entre maiusculas e minusculas.
    (cad. carateres x cad.carateres --> booleano)
    """

    def procurar_e_destruir(palavra, carater):
        """Remove a primeira ocorrencia de um carater numa string

        Recebe uma string e uma string apenas com um carater e, 
        utilizando o algoritmo de procura sequencial, devolve uma
        string com a primeira ocorrencia do carater eliminada, caso ele exista.
        No caso de não existir, é devolvida a string original.
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
    """Corrige texto com erros da documentação da BDB

    Recebe o texto da documentação da BDB com erros (string), 
    corrige as palavras com surto de letras e remove palavras sem sentido 
    que são anagramas das palavras anteriores. Retorna o texto corrigido (string).
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
    """Calcula o inteiro que corresponde à nova posição, após um movimento

    Recebe uma string apenas com um carater, que é a direção,
    e um inteiro, que é a posicao atual. Retorna um inteiro que corresponde 
    à nova posição.
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
    """Calcula o inteiro que corresponde à nova posição, após um ou mais movimentos

    Recebe uma string, que contem as direções, e um inteiro, que é a posição atual. 
    Retorna um inteiro que corresponde à nova posição.
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
        """Verifica se o argumento corresponde a uma sequencia de segurança de uma entrada da BDB

        Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde ao formato
        de uma sequencia de segurança de uma possivel entrada da BDB. 
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

    def sort_ocorrencias(lista):
        """Ordena a lista de ocorrencias de letras

        Ordena a lista (argumento) que contem listas que represetam a ocorrencia de letras 
        (i.e. [['a', 2], ['b', 3]]) por ordem inversa de ocorrencias de cada letra. 
        Caso duas letras tenham o mesmo numero de ocorrencias, o empate é decidido por
        ordem alfabética.
        Esta função baseia-se no algoritmo bubble sort.
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


def filtrar_bdb(entradas):
    """Determina as entradas da BDB em que a sequencia de controlo não é coerente com a cifra

    Recebe uma lista com uma ou mais entradas da BDB e devolve uma lista que contém as entradas
    em que a sequencia de controlo não é coerente com a cifra, na mesma ordem da lista original.
    (lista --> lista)
    """

    if not isinstance(entradas, list) or len(entradas) < 1:
        raise ValueError('filtrar_bdb: argumento invalido')

    lista_incoerentes = []
    for i in range(len(entradas)):

        if not eh_entrada(entradas[i]):
            raise ValueError('filtrar_bdb: argumento invalido')

        if not validar_cifra(entradas[i][0], entradas[i][1]):
            lista_incoerentes.append(entradas[i])

    return lista_incoerentes

# 4 - Desencriptação de dados


def obter_num_seguranca(tuplo):
    """Calcula o número de segunrança, através da sequência de segurança

    Recebe um tuplo correspondente à sequência de segurança e devolve um inteiro
    correspondente ao número de segurança. Este corresponde à menor diferença positiva
    entre qualquer par de números (da sequência de segurança).
    (tuplo --> inteiro)
    """

    ns = abs(tuplo[0] - tuplo[1])
    for i in range(len(tuplo)):
        for n in tuplo[i+1:]:

            dif = abs(tuplo[i] - n)
            if ns > dif:
                ns = dif

    return ns


def decifrar_texto(cifra, ns):
    """Decifra o texto encriptado através do número de segurança

    Recebe a cifra (string) e um número de segurança (int) e devolve
    o texto decifrado (string).
    (cad. carateres x inteiro --> cad. carateres)
    """

    def avancar_alfabeto(letra, avanco):
        """Avança a letra x posições no alfabeto

        Recebe uma letra (string apenas com um carater) e o número de posicoes
        a avançar (inteiro) e devolve a letra (string) do alfabeto que corresponde a esse avanço.
        (cad. carateres x inteiro --> cad. carateres)
        """

        res = chr(ord('a') + (ord(letra) - ord('a') + avanco) % 26)
        return res

    cifra_lista = list(cifra)

    for i in range(len(cifra_lista)):
        c = cifra_lista[i]
        if c == '-':
            cifra_lista[i] = ' '
            continue

        if i % 2 == 0:
            cifra_lista[i] = avancar_alfabeto(c, ns + 1)
        else:
            cifra_lista[i] = avancar_alfabeto(c, ns - 1)

    texto_decifrado = ''
    for c in cifra_lista:
        texto_decifrado += c

    return texto_decifrado


def decifrar_bdb(entradas):
    """Decifra as entradas da BDB

    Recebe uma lista de entradas da BDB e devolve, pela mesma ordem, uma lista com
    o seu texto decifrado.
    (lista --> lista)
    """

    if not isinstance(entradas, list) or len(entradas) < 1:
        raise ValueError('decifrar_bdb: argumento invalido')

    entradas_decifradas = []
    for i in range(len(entradas)):
        
        if not eh_entrada(entradas[i]):
            raise ValueError('decifrar_bdb: argumento invalido')
        
        ns = obter_num_seguranca(entradas[i][2])
        decifrada = decifrar_texto(entradas[i][0], ns)

        entradas_decifradas.append(decifrada)

    return entradas_decifradas

# 5 - Depuração de senhas


def eh_utilizador(argumento):
    """Verifica se o argumento corresponde à informação de um utilizador da BDB

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde a um dicionário
    que contem a informação de utilizador relevante da BDB (nome, senha e regra individual).
    Caso contrário, devolve False.
    (universal --> booleano)
    """

    def eh_name_pass(valor):
        """Verifica se o valor de 'name' e 'pass' estão no formato correto

        Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponder a um nome
        ou senha no formáto válido. Isto é, são strings com pelo menos 1 carater. 
        Caso contrário, devolve False.
        (universal --> booleano)
        """

        res = isinstance(valor, str) and len(valor) > 0
        return res

    def eh_vals(valor):
        """Verifica se o valor de 'vals' está no formato correto

        Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponder ao formato
        adequado do valor corresponde à chave 'vals', que pertence à regra individual. Isto é,
        um tuplo, com dois inteiros positivos, em que o primeiro é menor ou igual ao segundo.
        Caso contrário, devolve False.
        (universal --> booleano)
        """

        if isinstance(valor, tuple) and len(valor) == 2:
            if isinstance(valor[0], int) and isinstance(valor[1], int):
                if valor[0] <= valor[1]:
                    res = valor[0] > 0 and valor[1] > 0
                    return res

        return False

    def eh_rule(valor):
        """Verifica se o valor de 'rule' está no formato correto

        Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponder ao formato
        adequado correspondente à regra individual, isto é, um dicionário, que contém dois valores,
        um associado à chave 'vals' e outro associado à chave 'char', sendo este último uma string
        com apenas uma letra do alfabeto minuscula. Caso contrário, devolve False.
        (universal --> booleano)
        """

        if isinstance(valor, dict) and len(valor) == 2:
            if 'vals' in valor and 'char' in valor:

                if eh_vals(valor['vals']):

                    # Verifica char
                    char = valor['char']
                    if isinstance(char, str) and len(char) == 1:
                        return eh_alpha_lower(char)

        return False

    if isinstance(argumento, dict) and len(argumento) == 3:

        if 'name' in argumento and 'pass' in argumento and 'rule' in argumento:

            if eh_name_pass(argumento['name']):
                if eh_name_pass(argumento['pass']):
                    return eh_rule(argumento['rule'])

    return False


def eh_senha_valida(senha, rule):
    """Verifica se a senha é válida de acordo com as regras

    Recebe uma senha (string) e as regras individuais (dict) correspondentes a essa senha
    de um utilizador da BDB e devolve True se a senha estiver de acordo tanto com essas regras,
    como com as regras gerais. Isto é, tem pelo menos 3 vogais minusculas e contem pelo menos um 
    carater que aparece duas vezes consecutivas (regras gerais) e tem entre rule['vals'][0] e
    rule['vals'][1] número de ocorrencias do carater rule['char'] (regra individual).
    (cad. carateres x dicionário --> booleano)
    """

    # Regra Individual
    char_rule = rule['char']
    char_rule_count = 0

    vogais = ('a', 'e', 'i', 'o', 'u')

    # Regrais Gerais
    vogais_min = 0
    consecutivos = 0

    ultimo_char = ''
    for c in senha:

        # Regra Individual
        if c == char_rule:
            char_rule_count += 1

        # Regras Gerais
        if c in vogais:
            vogais_min += 1

        if ultimo_char == c:
            consecutivos += 1

        ultimo_char = c


    # Regra Individual
    char_rule_min = rule['vals'][0]
    char_rule_max = rule['vals'][1]
    res_individual = char_rule_min <= char_rule_count <= char_rule_max

    # Regras Gerais
    res_gerais = vogais_min >= 3 and consecutivos > 0

    res = res_individual and res_gerais
    return res


def filtrar_senhas(entradas):
    """Determina as entradas da BDB com senhas erradas
    
    Recebe uma lista com uma ou mais entradas da BDB e devolve uma lista ordenada alfabeticamente
    com os nomes dos utilizadores cujas senhas estão erradas.
    (lista --> lista)
    """

    def ordenar_alfabeticamente(lista):
        """Ordena uma lista de nomes

        Esta função baseia-se no algoritmo bubble sort e ordena uma lista de nomes por ordem alfabética. 
        A cada comparação, primeiro verifica se as duas palavras são iguais. Neste caso as suas posições não
        são alteradas.
        Se não forem iguais, compara carater a carater e determina assim o primeiro indice em que
        as palavras a ser comparadas sao diferentes. No caso de não se detetar um ponto de
        inequalidade, é considerada como primeira em ordem alfabética a de menor tamanho.
        (lista --> {})
        """

        alteracao = True
        
        for i in range(len(lista) - 1, 0, -1):

            alteracao = False

            for j in range(1, i + 1):

                # Selecionar indices das letras a comparar

                atual = lista[j]
                anterior = lista[j - 1]

                # Se duas entradas tiverem o mesmo nome
                if atual == anterior:
                    continue

                # Se a atual é menor do que a anterior
                tamanho_atual = len(atual)
                tamanho_anterior = len(anterior)
                found = False
                indice = 0
                if tamanho_atual < tamanho_anterior:

                    for k in range(tamanho_atual):
                        # Verificação se ambas não são letras
                        if not atual[k].isalpha() and not anterior[k].isalpha():
                            continue

                        if(atual[k] != anterior[k]):
                            indice = k       
                            found = True
                            break

                    if not found:
                        lista[j], lista[j - 1] = lista[j - 1], lista[j]
                        alteracao = True
                        continue

                # Se a anterior é menor do que a atual
                else:
                    
                    for k in range(tamanho_anterior):
                        # Verificação se ambas não são letras
                        if not atual[k].isalpha() and not anterior[k].isalpha():
                            continue

                        if (atual[k] != anterior[k]):
                            indice = k
                            found = True
                            break

                    if not found:
                        continue

                if primeiro_no_alfabeto(atual[indice], anterior[indice]):
                    lista[j], lista[j - 1] = lista[j - 1], lista[j]

                    alteracao = True

            if not alteracao:
                break
         
    if not isinstance(entradas, list) or len(entradas) < 1:
        raise ValueError('filtrar_senhas: argumento invalido')

    senhas_erradas = []
    for e in entradas:

        if not eh_utilizador(e):
            raise ValueError('filtrar_senhas: argumento invalido')

        if not eh_senha_valida(e['pass'], e['rule']):
            senhas_erradas.append(e['name'])

    ordenar_alfabeticamente(senhas_erradas)

    return senhas_erradas

