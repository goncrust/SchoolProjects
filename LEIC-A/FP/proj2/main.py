"""FP - Segundo Projeto (O Prado)

Este projeto consiste em escrever um programa que simule o ecossistema de um prado
em que convivem animais que se movimentam, alimentam, reproduzem e morrem.
Para isto definiu-se um conjunto de TADs: 
    posicao - representar e manipular posições (x, y) de um prado arbitrariamente grande.
    animal  - representar e manipular os animais do simulador, existindo dois tipos:
              predadores e presas.
              As presas são caraterizadas pela espécie, idade e frequência de reprodução.
              Os predadores são caraterizados pelas mesmas propriedades das presas e ainda
              pela fome e frequência de alimentação. As presas têm estes dois atributos
              sempre iguais a zero.
    prado   - representar e manipular uma estrutura retangular com um número definido
              de posições que estão ocupadas por montanhas (a toda a volta), rochedos,
              animais ou até mesmo nada (espaço vazio).
e duas funções adicionais para concretizar a simulação.
Uma breve descrição do comportamento e regras:
    A cada geração, cada animal, seguindo a ordem de leitura do prado (esquerda para a direita,
    seguida de cima para baixo), realiza o seu turno de ação. Neste, a sua idade
    e fome (em predadores) são incrementadas, e em seguida tenta realizar um movimento,
    reproduzir-se (se idade >= frequência de reprodução, voltando a sua idade para zero
    e deixando na posição de onde veio um novo animal igual a ele, com fome e idade igual a zero.
    Caso não se movimente não pode reproduzir), alimentar-se (em predadores se comer uma presa,
    voltando a sua fome para zero) ou morrer (em predadores se fome == frequência de alimentação,
    ou em presa, se comida por um predador).
    Quanto ao movimento, os animais movem-se para cima, para baixo, para a esquerda ou para a
    direita. Os predadores, se puderem movimentam-se para cima de uma presa e comem-na, se não,
    movimenta-se para uma posição livre, tal como as presas. No caso de haver mais que uma opção
    de movimentação, numeram-se as mesmas começando de 0, no sentido horário a partir da
    posição 12:00 e a posição selecionada é dada por N (mod p), onde N é o valor numérico que
    respeita a ordem de leitura (ex: N((0, 0)) = 0, N((2,1)) = 9) e p é o número de opções
    de movimentação possíveis.
"""

# TAD posicao - Operação Básicas


def cria_posicao(x, y):
    """Construtor do TAD posicao

    Recebe dois inteiros correspondentes às coordenadas da posicao (x, y), e devolve
    uma nova posicao, com essas coordenadas. Verifica a validade dos argumentos.
    A utilização da lista como representação interna é devida à necessidade de ser
    mutável, e à não necessidade de ser do tipo dicionário (demasiado complexo).
    (int x int --> posicao)
    """

    if not isinstance(x, int) or not isinstance(y, int) or x < 0 or y < 0:
        raise ValueError('cria_posicao: argumentos invalidos')

    return [x, y]


def cria_copia_posicao(p):
    """Construtor do TAD posicao que cria uma cópia de uma posicao existente

    Recebe uma posicao e devolve uma nova posicao igual à recebida.
    (posicao --> posicao)
    """

    return p.copy()


def obter_pos_x(p):
    """Devolve a componente x da posicao

    Recebe uma posicao e devolve a sua componente x (abcissa).
    (posicao --> int)
    """

    return p[0]


def obter_pos_y(p):
    """Devolve a componente y da posicao

    Recebe uma posicao e devolve a sua componente y (ordenada).
    (posicao --> int)
    """

    return p[1]


def eh_posicao(arg):
    """Determina se é do tipo posicao

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD posicao. Caso contrário devolve False.
    (universal --> booleano)
    """

    return isinstance(arg, list) and len(arg) == 2 and \
            isinstance(arg[0], int) and isinstance(arg[1], int) \
            and arg[0] >= 0 and arg[1] >= 0


def posicoes_iguais(p1, p2):
    """Verifica se as posicoes são iguais

    Recebe dois TAD posicao e devolve True apenas se ambos representam a mesma posicao.
    Caso contrário devolve False.
    (posicao x posicao --> booleano)
    """

    return p1 == p2


def posicao_para_str(p):
    """Representação externa do tipo posicao

    Devolve uma string que corresponde à representação externa do TAD posicao.
    (posicao --> str)
    """

    return f'({p[0]}, {p[1]})'

# TAD posicao - Funções de alto nível


def obter_posicoes_adjacentes(p):
    """Determina as posições adjacentes

    Recebe uma posicao e devolve um tuplo com as posições adjacentes à mesma,
    começando pela posição acima da recebida e seguindo no sentido horário.
    Não é possível criar uma posição com x ou y menor do que 0, pelo que é primeiro
    verificado se a posição recebida tem x ou y igual a zero. No caso de x ser igual a 0,
    a posição à esquerda não faz parte do tuplo final. No caso do y ser igual a 0, a posição
    acima não faz parte do tuplo final.
    (posicao --> tuplo)
    """

    x = obter_pos_x(p)
    y = obter_pos_y(p)

    res = ()
    if y != 0:
        res += (cria_posicao(x, y-1),)
    res += (cria_posicao(x+1, y), cria_posicao(x, y+1))
    if x != 0:
        res += (cria_posicao(x-1, y),)

    return res


def ordenar_posicoes(t):
    """Ordena um conjunto de posições de acordo com a ordem de leitura do prado

    Recebe um tuplo com um conjunto de TADs posicao e devolve um tuplo com as posições
    recebidas ordenadas de acordo com a ordem de leitura do prado. Esta função baseia-se
    no algoritmo Bubble Sort.
    (tuplo --> tuplo)
    """

    def posicao_anterior(p1, p2):
        """Determina se uma posicao é anterior a outra

        Recebe duas posições e devolve True se a primeira é anterior à segunda,
        segundo a ordem de leitura do prado. Caso contrário devolve False.
        (posicao x posicao --> booleano)
        """

        if obter_pos_y(p1) == obter_pos_y(p2):
            return obter_pos_x(p1) < obter_pos_x(p2)
        return obter_pos_y(p1) < obter_pos_y(p2)
    
    # é mais fácil e eficiente transformar em lista para ordenar
    l = list(t)
    alteracao = True
    for i in range(len(t)-1, 0, -1):
        alteracao = False
        for j in range(1, i+1):
            if posicao_anterior(l[j], l[j-1]):
                l[j], l[j-1] = l[j-1], l[j]
                alteracao = True
        if not alteracao:
            break

    return tuple(l)

# TAD animal - Operações Básicas


def cria_animal(s, r, a):
    """Construtor do TAD animal

    Recebe uma string (espécie do animal) e dois inteiros (frequência de reprodução e
    frequência de alimentação, respetivamente), e devolve um novo animal, com essas propriedades.
    Se a frequência de alimentação for 0, o animal é considerado uma presa, caso contrário é
    considerado um predador. Verifica a validade dos argumentos.
    A escolha do dicionário como representação interna deve-se a ser mais fácil
    a identificação, e por sua vez a escrita e leitura, do código das operações básicas
    que acede ou modifica as diferentes propriedades do animal. É também necessário que
    seja possível modificar destrutivamente o mesmo, sendo essa uma carateristica
    do tipo dicionário.
    (str x int x int --> animal)
    """

    if not isinstance(s, str) or not isinstance(r, int) or not isinstance(a, int) \
            or s == '' or r < 1 or a < 0:

        raise ValueError('cria_animal: argumentos invalidos')

    return {'especie': s, 'freq_reproducao': r, 'freq_alimentacao': a, 'idade': 0, 'fome': 0}


def cria_copia_animal(a):
    """Construtor do TAD animal que cria uma cópia de um animal existente

    Recebe um animal e devolve um novo animal igual ao recebido (com os mesmos atributos).
    (animal --> animal)
    """

    return a.copy()


def obter_especie(a):
    """Devolve a espécie do animal

    Recebe um animal e devolve a sua espécie.
    (animal --> str)
    """

    return a['especie']


def obter_freq_reproducao(a):
    """Devolve a frequência de reprodução do animal

    Recebe um animal e devolve a sua frequência de reprodução.
    (animal --> int)
    """

    return a['freq_reproducao']


def obter_freq_alimentacao(a):
    """Devolve a frequência de alimentação do animal

    Recebe um animal e devolve a sua frequência de alimentação.
    (animal --> int)
    """

    return a['freq_alimentacao']


def obter_idade(a):
    """Devolve a idade do animal

    Recebe um animal e devolve a sua idade.
    (animal --> int)
    """

    return a['idade']


def obter_fome(a):
    """Devolve a fome do animal

    Recebe um animal e devolve o valor da sua fome.
    (animal --> int)
    """

    return a['fome']


def aumenta_idade(a):
    """Incrementa a idade do animal

    Recebe um animal e modifica-o destrutivamente, incrementando a sua idade.
    Devolve o próprio animal.
    (animal --> animal)
    """

    a['idade'] += 1
    return a


def reset_idade(a):
    """Repõe a idade do animal para zero

    Recebe um animal e modifica-o destrutivamente, repondo o valor da sua idade para zero.
    Devolve o próprio animal.
    (animal --> animal)
    """

    a['idade'] = 0
    return a


def aumenta_fome(a):
    """Incrementa a fome do animal

    Recebe um animal e modifica-o destrutivamente, incrementando a sua fome.
    Devolve o próprio animal.
    (animal --> animal)
    """

    if eh_predador(a):
        a['fome'] += 1
    return a


def reset_fome(a):
    """Repõe a fome do animal para zero

    Recebe um animal e modifica-o destrutivamente, repondo o valor da sua fome para zero.
    Devolve o próprio animal.
    (animal --> animal)
    """

    if eh_predador(a):
        a['fome'] = 0
    return a


def eh_animal(arg):
    """Determina se corresponde ao tipo animal

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD animal. Caso contrário devolve False.
    (universal --> booleano)
    """
    return isinstance(arg, dict) and len(arg) == 5 and \
            'especie' in arg and 'freq_reproducao' in arg and 'freq_alimentacao' in arg and \
            'idade' in arg and 'fome' in arg and \
            isinstance(arg['especie'], str) and isinstance(arg['freq_reproducao'], int) and \
            isinstance(arg['freq_alimentacao'], int) and isinstance(arg['idade'], int) and \
            isinstance(arg['fome'], int) and arg['especie'] != '' and \
            arg['freq_reproducao'] >= 1 and arg['freq_alimentacao'] >= 0 and \
            arg['idade'] >= 0 and arg['fome'] >= 0


def eh_predador(arg):
    """Determina se corresponde a um animal predador

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD animal, predador. Um animal é predador se a sua frequência de alimentação
    for superior a zero. Caso não seja devolve False.
    (universal --> booleano)
    """

    return eh_animal(arg) and arg['freq_alimentacao'] > 0


def eh_presa(arg):
    """Determina se corresponde a um animal presa

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD animal, presa. Um animal é presa se a sua frequência de alimentação
    for igual a zero. Caso não seja devolve False.
    (universal --> booleano)
    """

    return eh_animal(arg) and arg['freq_alimentacao'] == 0


def animais_iguais(a1, a2):
    """Verifica se os animais são iguais

    Recebe dois TAD animal e devolve True apenas se ambos representam o mesmo animal,
    isto é, a mesma espécie e atributos.
    Caso contrário devolve False.
    (animal x animal --> booleano)
    """

    return eh_animal(a1) and eh_animal(a2) and a1 == a2


def animal_para_char(a):
    """Representação externa da espécie

    Recebe um animal e devolve uma string de um único elemento que corresponde ao primeiro 
    carácter da espécie do animal, em maiúscula se for predador e em minúscula se for presa.
    (animal --> str)
    """

    c = obter_especie(a)[0]
    return c.lower() if eh_presa(a) else c.upper()


def animal_para_str(a):
    """Representação externa do tipo animal

    Devolve uma string que corresponde à representação externa do TAD animal.
    (animal --> str)
    """

    res = f"{obter_especie(a)} [{obter_idade(a)}/{obter_freq_reproducao(a)}"
    if eh_predador(a):
        res += f";{obter_fome(a)}/{obter_freq_alimentacao(a)}"

    return res + ']'

# TAD animal - Funções de alto nível


def eh_animal_fertil(a):
    """Verifica se o animal atingiu a idade de reprodução

    Recebe um animal e devolve True se o mesmo atingiu um valor de idade igual
    ou superior à sua frequência de reprodução.
    Caso contrário devolve False.
    (animal --> booleano)
    """

    return obter_idade(a) >= obter_freq_reproducao(a)


def eh_animal_faminto(a):
    """Verifica se o animal atingiu o estado de faminto

    Recebe um animal e devolve True se o mesmo atingiu um valor de fome igual
    à sua frequência de alimentação (vai morrer). Caso contrário devolve False. 
    As presas devolvem sempre False.
    (animal --> booleano)
    """

    return False if eh_presa(a) else obter_fome(a) == obter_freq_alimentacao(a)


def reproduz_animal(a):
    """Efetua a reprodução de um animal

    Recebe um animal e devolve um novo animal da mesma espécie com idade e fome igual a zero.
    O animal que deu origem a este (argumento) é modificado destrutivamente, alterando a sua
    idade para zero.
    (animal --> animal)
    """

    reset_idade(a)
    return reset_fome(cria_copia_animal(a))

# TAD prado - Operações Básicas


# Função auxiliar
def obter_indice_pos_tuplo(t, p):
    """Indíce de um TAD posicao no tuplo

    Recebe um tuplo e uma posicao e devolve o indíce em que essa posicao aparece no tuplo
    pela primeira vez. Caso a mesma não esteja presente no tuplo, é devolvido -1.
    (tuplo x posicao --> int)
    """

    for i in range(len(t)):
        if posicoes_iguais(t[i], p):
            return i

    return -1


# Função auxiliar
def existe_pos_repetida(t):
    """Verifica posições repetidas num tuplo

    Recebe um tuplo com elementos que são do tipo posicao e devolve True caso alguma
    esteja repetida no tuplo. Caso contrário devolve False.
    (tuplo --> booleano)
    """

    for i in range(1, len(t)):
        if obter_indice_pos_tuplo(t[:i], t[i]) != -1:
            return True

    return False


# Função auxiliar
def eh_limite_exterior_prado(ult_pos, p):
    """Verifica se posicao pertence aos limites exteriores do prado

    Recebe a posicao que corresponde à montanha do canto inferior direito do prado
    e uma posicao. Devolve True caso a segunda faça parte dos limites exteriores do
    prado. Caso contrário devolve False.
    (posicao x posicao --> booleano)
    """

    x = obter_pos_x(p)
    y = obter_pos_y(p)
    return x <= 0 or y <= 0 or x >= obter_pos_x(ult_pos) or y >= obter_pos_y(ult_pos)


def cria_prado(d, r, a, p):
    """Construtor do TAD prado

    Recebe uma posicao (da montanha do canto inferior direito do prado), um tuplo com zero
    ou mais posições (de rochedos que não são montanhas dos limites exteriores do prado),
    um tuplo com um ou mais animais e um tuplo do mesmo tamanho do anterior com as posições
    correspondentes aos animais. Devolve um novo prado com os atributos recebidos.
    Verifica a validade dos argumentos:
        1. Tipos dos argumentos
        2. Tamanho dos tuplos
        3. Tipos dos elementos dos tuplos
        4. Se existem rochedos nos limites exteriores
        5. Se existem animais nos limites exteriores
        6. Se existem animais em rochedos
        7. Se existem posições de animais/rochedos repetidas
    A escolha do dicionário como representação interna deve-se a ser mais fácil
    a identificação, e por sua vez a escrita e leitura, do código das operações básicas
    que acede ou modifica as diferentes propriedades do prado. É também necessário que
    seja possível modificar destrutivamente o mesmo, sendo essa uma carateristica
    do tipo dicionário.
    (posicao x tuplo x tuplo x tuplo --> prado)
    """

    if not eh_posicao(d) or not isinstance(r, tuple) or not isinstance(a, tuple) or \
            not isinstance(p, tuple) or len(a) < 1 or len(a) != len(p) or \
            not all(map(lambda x: eh_posicao(x) and not eh_limite_exterior_prado(d, x), r)) or \
            not all(map(lambda x, y: eh_animal(x) and eh_posicao(y) 
                and not eh_limite_exterior_prado(d, y) 
                and obter_indice_pos_tuplo(r, y) == -1, a, p)) or \
            existe_pos_repetida(r) or existe_pos_repetida(p):

        raise ValueError('cria_prado: argumentos invalidos')

    return {'ult_pos': d, 'rochedos': r, 'animais': a, 'pos_animais': p}


def cria_copia_prado(m):
    """Construtor do TAD prado que cria uma cópia de um prado existente

    Recebe um prado e devolve um novo prado igual ao recebido.
    (prado --> prado)
    """

    return m.copy()


def obter_tamanho_x(m):
    """Devolve o tamanho do prado no eixo x

    Recebe um prado e devolve o tamanho do seu eixo x.
    (prado --> int)
    """

    return obter_pos_x(m['ult_pos']) + 1


def obter_tamanho_y(m):
    """Devolve o tamanho do prado no eixo y

    Recebe um prado e devolve o tamanho do seu eixo y.
    (prado --> int)
    """

    return obter_pos_y(m['ult_pos']) + 1


def obter_numero_predadores(m):
    """Devolve o número de predadores no prado

    Recebe um prado e devolve o número de predadores atualmento no mesmo.
    (prado --> int)
    """

    return len([x for x in m['animais'] if eh_predador(x)])


def obter_numero_presas(m):
    """Devolve o número de presas no prado

    Recebe um prado e devolve o número de presas atualmento no mesmo.
    (prado --> int)
    """

    return len([x for x in m['animais'] if eh_presa(x)])


def obter_posicao_animais(m):
    """Devolve o tuplo corresponde às posições dos animais

    Recebe um prado e devolve o tuplo que contem as posições dos animais do prado,
    ordenadas em ordem de leitura do prado.
    (prado --> tuplo posicoes)
    """

    return ordenar_posicoes(m['pos_animais'])


def obter_animal(m, p):
    """Devolve o animal na posicao

    Recebe um prado e uma posicao e devolve o animal que está nessa posicao, no prado.
    Caso não exista um animal nessa posicao, devolve None.
    (prado x posicao --> animal)
    """

    i = obter_indice_pos_tuplo(m['pos_animais'], p)
    return m['animais'][i] if i != -1 else None


def eliminar_animal(m, p):
    """Elimina o animal na posicao

    Recebe um prado e uma posicao e altera destrutivamente o prado, eliminando o animal
    que estava nessa posicao. Caso o animal não exista, nada é alterado.
    Devolve o próprio prado.
    (prado x posicao --> prado)
    """

    i = obter_indice_pos_tuplo(m['pos_animais'], p)
    if i != -1:
        # eliminar animal e posicao do animal
        m['pos_animais'] = m['pos_animais'][:i] + m['pos_animais'][i+1:]
        m['animais'] = m['animais'][:i] + m['animais'][i+1:]

    return m


def mover_animal(m, p1, p2):
    """Move o animal de uma posicao para outra

    Recebe um prado e duas variáveis do tipo posicao e altera destrutivamente o prado,
    movendo o animal que estava na primeira posicao, para a segunda.
    Caso o animal não exista ou a posicao de destino já esteja ocupada, nada é alterado.
    Devolve o próprio prado.
    (prado x posicao x posicao --> prado)
    """

    i = obter_indice_pos_tuplo(m['pos_animais'], p1)
    if i != -1 and eh_posicao_livre(m, p2):
        m['pos_animais'] = m['pos_animais'][:i] + (p2,) + m['pos_animais'][i+1:]

    return m


def inserir_animal(m, a, p):
    """Insere um novo animal numa posicao

    Recebe um prado, um animal e uma posicao e altera destrutivamente o prado,
    inserindo o novo animal na posicao indicada. Caso a posicao já esteja ocupada,
    nada é alterado. Devolve o próprio prado.
    (prado x animal x posicao --> prado)
    """

    if eh_posicao_livre(m, p):
        m['animais'] += (a,)
        m['pos_animais'] += (p,)

    return m


def eh_prado(arg):
    """Determina se corresponde ao tipo prado

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD prado. Caso contrário devolve False.
    Condições verificadas:
        1. Tipo do argumento
        2. Chaves do dicionário
        3. Tipo dos valores
        4. Tamanho dos tuplos
        5. Tipos dos elementos dos tuplos
        6. Se existem rochedos nos limites exteriores
        7. Se existem animais nos limites exteriores
        8. Se existem animais em rochedos
        9. Se existem posições de animais/rochedos repetidas
    (universal --> booleano)
    """

    return isinstance(arg, dict) and len(arg) == 4 and \
            'ult_pos' in arg and 'rochedos' in arg and 'animais' in arg and 'pos_animais' in arg \
            and eh_posicao(arg['ult_pos']) and isinstance(arg['rochedos'], tuple) and \
            isinstance(arg['animais'], tuple) and isinstance(arg['pos_animais'], tuple) and \
            len(arg['animais']) > 0 and len(arg['animais']) == len(arg['pos_animais']) and \
            all(map(lambda x: eh_posicao(x) and not eh_limite_exterior_prado(arg['ult_pos'], x), 
                arg['rochedos'])) and \
            all(map(lambda x, y: eh_animal(x) and eh_posicao(y)
                and not eh_limite_exterior_prado(arg['ult_pos'], y)
                and obter_indice_pos_tuplo(arg['rochedos'], y) == -1, 
                arg['animais'], arg['pos_animais'])) and \
            not existe_pos_repetida(arg['rochedos']) and \
            not existe_pos_repetida(arg['pos_animais'])


def eh_posicao_animal(m, p):
    """Verifica se existe um animal na posicao

    Recebe um prado e uma posicao e devolve True se existir um animal
    nessa posicao. Caso contrário, devolve False.
    (prado x posicao --> booleano)
    """

    return obter_indice_pos_tuplo(m['pos_animais'], p) != -1


def eh_posicao_obstaculo(m, p):
    """Verifica se existe um obstáculo na posicao

    Recebe um prado e uma posicao e devolve True se existir um obstáculo
    (rochedo/montanha) nessa posicao. Caso contrário, devolve False.
    (prado x posicao --> booleano)
    """

    return obter_indice_pos_tuplo(m['rochedos'], p) != -1 or eh_limite_exterior_prado(m['ult_pos'], p)


def eh_posicao_livre(m, p):
    """Verifica se a posicao está livre

    Recebe um prado e uma posicao e devolve True se não existir nem um obstáculo
    (rochedo/montanha) nem um animal nessa posicao (ou seja, está livre).
    Caso contrário, devolve False.
    (prado x posicao --> booleano)
    """

    return not (eh_posicao_animal(m, p) or eh_posicao_obstaculo(m, p))


def prados_iguais(p1, p2):
    """Verifica se os prados são iguais

    Recebe dois TAD prado e devolve True apenas se ambos representam o mesmo prado.
    Caso contrário devolve False.
    (prado x prado --> booleano)
    """

    # O prado é constituido pelos TADs posicao e animal, pelo que não pode ser comparado com '=='
    return eh_prado(p1) and eh_prado(p2) and \
            posicoes_iguais(p1['ult_pos'], p2['ult_pos']) and \
            len(p1['rochedos']) == len(p2['rochedos']) and \
            all(map(lambda x, y: posicoes_iguais(x, y), p1['rochedos'], p2['rochedos'])) and \
            len(p1['animais']) == len(p2['animais']) and \
            all(map(lambda x, y: animais_iguais(x, y), p1['animais'], p2['animais'])) and \
            all(map(lambda x, y: posicoes_iguais(x, y), p1['pos_animais'], p2['pos_animais']))


def prado_para_str(m):
    """Representação externa do tipo prado

    Devolve uma string que corresponde à representação externa do TAD prado.
    (prado --> str)
    """

    tamanho_x = obter_tamanho_x(m)
    topo_fim = '+' + '-'*(tamanho_x - 2) + '+'

    # topo
    res = topo_fim + '\n'

    # linha por linha
    for y in range(1, obter_tamanho_y(m) - 1):
        res += '|'
        # coluna por coluna
        for x in range(1, tamanho_x - 1):
            pos = cria_posicao(x, y)
            
            # caso de ser um rochedo
            if eh_posicao_obstaculo(m, pos):
                res += '@'
            else:
                # caso de ser um animal
                i = obter_indice_pos_tuplo(m['pos_animais'], pos)
                if i != -1:
                    res += animal_para_char(m['animais'][i])
                # caso de estar livre
                else:
                    res += '.'
        res += '|\n'

    # fim
    res += topo_fim

    return res

# TAD prado - Funções de alto nível


def obter_valor_numerico(m, p):
    """Devolve o número da posicao correspondente à ordem de leitura

    Recebe um prado e uma posicao e devolve o número correspondente a essa,
    de acordo com a ordem de leitura do prado.
    (prado x posicao --> int)
    """

    return obter_pos_y(p) * obter_tamanho_x(m) + obter_pos_x(p)


def obter_movimento(m, p):
    """Devolve a posicao seguinte do animal

    Recebe um prado e uma posicao e devolve a posicao seguinte do animal que se encontra
    na posicao recebida, de acordo com as regras de movimento dos animais do prado.
    Caso o animal não exista ou não existam posições livres para se mover, devolve a
    posição de entrada (onde o animal vai permanecer).
    (prado x posicao --> posicao)
    """

    # verificar que o animal existe 
    if not eh_posicao_animal(m, p):
        return p

    movs_possiveis = obter_posicoes_adjacentes(p)

    # predador
    if eh_predador(obter_animal(m, p)):
        # selecionar presas
        temp = movs_possiveis
        movs_possiveis = [x for x in movs_possiveis if
                eh_posicao_animal(m, x) and eh_presa(obter_animal(m, x))]

        # retirar obstáculos/predadores caso nao existirem presas para comer
        if not len(movs_possiveis):
            movs_possiveis = temp
            movs_possiveis = [x for x in movs_possiveis if eh_posicao_livre(m, x)]
    # presa
    else:
        # retirar obstáculos/animais
        movs_possiveis = [x for x in movs_possiveis if eh_posicao_livre(m, x)]

    nmr_opcoes = len(movs_possiveis)
    if not nmr_opcoes:
        return p
    posicao_selecionada = obter_valor_numerico(m, p) % nmr_opcoes
    return movs_possiveis[posicao_selecionada]

# Funções adicionais


def geracao(m):
    """Avança uma geração

    Recebe um prado e modifica-o de acordo com a evolução correspondente a uma
    geração completa, isto é, cada animal (vivo) realiza o seu turno de ação de
    acordo com as regras descritas. Devolve o prado.
    (prado --> prado)
    """

    # manter registo animais que morreram, para não realizar um turno de ação
    # duplicado de um outro animal que calhe na posição do que morreu
    animais_mortos = []
    # percorrer todos os animais (por ordem de leitura do prado)
    for p in obter_posicao_animais(m):
        # verificar se aqui morreu um animal (para não repetir o turno do animal, erradamente)
        if obter_indice_pos_tuplo(animais_mortos, p) != -1:
            continue

        animal = obter_animal(m, p)

        # incrementar atributos
        aumenta_idade(animal)
        aumenta_fome(animal)

        # mover
        prox_posicao = obter_movimento(m, p)
        mover = not posicoes_iguais(prox_posicao, p)
        if mover:
            # alimentar
            if eh_posicao_animal(m, prox_posicao):
                eliminar_animal(m, prox_posicao)
                animais_mortos.append(prox_posicao)
                reset_fome(animal)

            mover_animal(m, p, prox_posicao)

            # reproduzir
            if eh_animal_fertil(animal):
                inserir_animal(m, reproduz_animal(animal), p)

        # morrer
        if eh_animal_faminto(animal):
            eliminar_animal(m, prox_posicao if mover else p)
            animais_mortos.append(prox_posicao)

    return m


def simula_ecossistema(f, g, v):
    """Simula o ecossistema de um prado

    Recebe o nome de um ficheiro (string) correspondente à configuração da simulação,
    um inteiro que representa o número de gerações a simular e um booleano que, no caso
    de ser True, ativa o modo verboso, caso contrário ativa o modo quiet.
    O modo quiet apenas mostra a informação e o prado na primeira e última geração.
    O modo verboso mostra a informação e o prado todas as gerações em que à uma alteração
    no número de predadores ou presas.
    O ficheiro de configuração contém, na primeira linha, a representação externa do canto
    inferior direito do prado, na segunda linha, a representação externa das posições dos rochedos
    do prado e, nas restantes linhas, um animal diferente do prado caracterizado pelo seu nome,
    frequência de reprodução, frequência de alimentação e a representação externa da posição que
    ocupa inicialmente no prado.
    Devolve o número de predadores e presas no prado no fim da simulação.
    (str x int x booleano --> tuplo)
    """

    def escrever_info_prado(prado, predadores, presas, gen):
        """Escreve o prado e informação

        Recebe o prado, o número de predadores atualmente no mesmo,
        o número de presas atualmente no mesmo e a geração atual e escreve
        no ecrã a saída standard (o número de predadores, presas, geração
        e o próprio prado (representação externa)).
        Os predadores e presas não são calculados através do prado mas sim
        recebidos em argumento por uma questão de eficiencia, pois sempre
        que esta função é chamada, estes dados já foram usados anteriormente
        e estão guardados numa variável.
        (prado x int x int x int --> {})
        """

        print(f'Predadores: {predadores} vs Presas: {presas} (Gen. {gen})')
        print(prado_para_str(prado))

    # ler configuração
    with open(f, 'r') as ficheiro:
        lines = tuple(map(lambda x: eval(x), ficheiro.readlines()))

    # criar prado
    ult_pos = cria_posicao(lines[0][0], lines[0][1])
    rochedos = tuple(map(lambda x: cria_posicao(x[0], x[1]), lines[1]))
    animais = ()
    pos_animais = ()
    for l in lines[2:]:
        animais += (cria_animal(l[0], l[1], l[2]),)
        pos_animais += (cria_posicao(l[3][0], l[3][1]),)
    prado = cria_prado(ult_pos, rochedos, animais, pos_animais)

    # Gen 0
    predadores_antes = obter_numero_predadores(prado)
    presas_antes = obter_numero_presas(prado)
    escrever_info_prado(prado, predadores_antes, presas_antes, 0)

    # Gens
    for gen in range(1, g+1):
        geracao(prado)

        predadores = obter_numero_predadores(prado)
        presas = obter_numero_presas(prado)
        if v and (predadores != predadores_antes or presas != presas_antes):
            escrever_info_prado(prado, predadores, presas, gen)

        predadores_antes = predadores
        presas_antes = presas

    # última Gen
    if not v:
        escrever_info_prado(prado, predadores_antes, presas_antes, g)

    return (predadores_antes, presas_antes)

