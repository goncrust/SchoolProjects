"""FP - Segundo Projeto (O Prado)
"""

# TAD posicao - Operação Básicas


def cria_posicao(x, y):
    """Construtor do TAD posicao

    Recebe dois inteiros correspondentes às coordenadas da posicao (x, y), e devolve
    uma nova variável do tipo posicao, com essas coordenadas. Verifica a validade dos
    argumentos.
    (int x int --> posicao)
    """

    if not isinstance(x, int) or not isinstance(y, int) or x < 0 or y < 0:
        raise ValueError('cria_posicao: argumentos invalidos')

    return (x, y)


def cria_copia_posicao(p):
    """Construtor do TAD posicao que cria uma cópia de uma posicao existente

    Recebe uma posicao e devolve uma nova posicao igual à recebida.
    (posicao --> posicao)
    """

    return p[0:]


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
    """Determina se uma variavel corresponde ao tipo posicao

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD posicao. Caso contrário devolve False.
    (universal --> booleano)
    """
            # Propriedades do tuplo
            # Tipo dos elementos
            # Propriedades dos elementos
    return isinstance(arg, tuple) and len(arg) == 2 and \
            isinstance(arg[0], int) and isinstance(arg[1], int) \
            and arg[0] >= 0 and arg[1] >= 0


def posicoes_iguais(p1, p2):
    """Verifica se as posicoes são iguais

    Recebe dois TAD posicao e devolve True apenas se ambos representam a mesma posicao.
    Caso contrário devolve False.
    (posicao x posicao --> booleano)
    """

    return eh_posicao(p1) and eh_posicao(p2) and p1 == p2


def posicao_para_str(p):
    """Representação externa do tipo posicao

    Devolve uma string que corresponde à representação externa do TAD posicao.
    (posicao --> str)
    """

    return f'({p[0]}, {p[1]})'

# TAD posicao - Funções de alto nível


def obter_posicoes_adjacentes(p):
    """Determina as posicoes adjacentes

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
    recebidas ordenadas de acordo com a ordem de leitura do prado.
    (tuplo --> tuplo)
    """

    return sorted(t, lambda x: (x[1], x[0]))

# TAD animal - Operações Básicas


def cria_animal(s, r, a):
    """Construtor do TAD animal

    Recebe uma string (espécie do animal) e dois inteiros (frequência de reprodução e
    frequência de alimentação, respetivamente), e devolve uma nova variável do tipo animal,
    com essas propriedades. Se a frequência de alimentação for 0, o animal é considerado 
    uma presa, caso contrário é considerado um predador. Verifica a validade dos argumentos.
    (str x int x int --> animal)
    """

    if not isinstance(s, str) or not isinstance(r, int) or not isinstance(a, int) \
            or r < 0 or a < 0:

        raise ValueError('cria_animal: argumentos invalidos')

    return {'especie': s, 'freq_reproducao': r, 'freq_alimentacao': a, 'idade': 0, 'fome': 0}


def cria_copia_animal(a):
    """Construtor do TAD animal que cria uma cópia de um animal existente

    Recebe uma variável animal e devolve uma nova variável animal igual ao recebido (com as
    mesmas propriedades).
    (animal --> animal)
    """

    return {'especie': a['especie'], 'freq_reproducao': a['freq_reproducao'],
            'freq_alimentacao': a['freq_alimentacao'], 'idade': 0, 'fome': 0}


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

    a['fome'] += 1
    return a


def reset_fome(a):
    """Repõe a fome do animal para zero

    Recebe um animal e modifica-o destrutivamente, repondo o valor da sua fome para zero.
    Devolve o próprio animal.
    (animal --> animal)
    """

    a['fome'] = 0
    return a


def eh_animal(arg):
    """Determina se uma variável corresponde ao tipo animal

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD animal. Caso contrário devolve False.
    (universal --> booleano)
    """
            # Propriedades do dicionário
            # Chaves do dicionário

            # Tipo dos valores


            # Propriedades dos valores

    return isinstance(arg, dict) and len(arg) == 5 and \
            'especie' in arg and 'freq_reproducao' in arg and 'freq_alimentacao' in arg and \
            'idade' in arg and 'fome' in arg and \
            isinstance(arg['especie'], str) and isinstance(arg['freq_reproducao'], int) and \
            isinstance(arg['freq_alimentacao'], int) and isinstance(arg['idade'], int) and \
            isinstance(arg['fome'], int) and \
            arg['freq_reproducao'] >= 0 and  arg['freq_alimentacao'] >= 0 and \
            arg['idade'] >= 0 and arg['fome'] >= 0


def eh_predador(arg):
    """Determina se uma variável corresponde a um animal predador

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD animal, predador. Um animal é predador se a sua frequência de alimentação
    for superior a zero. Caso não seja devolve False.
    (universal --> booleano)
    """

    return eh_animal(arg) and arg['freq_alimentacao'] > 0


def eh_presa(arg):
    """Determina se uma variável corresponde a um animal presa

    Recebe um argumento de qualquer tipo e devolve True se o mesmo corresponde
    a um TAD animal, presa. Um animal é presa se a sua frequência de alimentação
    for igual a zero. Caso não seja devolve False.
    (universal --> booleano)
    """

    return eh_animal(arg) and arg['freq_alimentacao'] == 0


def animais_iguais(a1, a2):
    """Verifica se os animais são iguais

    Recebe dois TAD animal e devolve True apenas se ambos representam o mesmo animal,
    isto é, a mesma espécie e propriedades.
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

    if eh_predador(a):
        return a['especie'][0].upper()
    elif eh_presa(a):
        return a['especie'][0].lower()


def animal_para_str(a):
    """Representação externa do tipo animal

    Devolve uma string que corresponde à representação externa do TAD animal.
    (animal --> str)
    """

    res = f"{a['especie']} [{a['idade']}/{a['freq_reproducao']}"
    if eh_predador(a):
        res += f";{a['fome']}/{a['freq_alimentacao']}"

    return res + ']'

# TAD animal - Funções de alto nível


def eh_animal_fertil(a):
    """Verifica se o animal atingiu a idade de reprodução

    Recebe um animal e devolve True se o mesmo atingiu a idade de reprodução.
    Caso contrário devolve False.
    (animal --> booleano)
    """

    return obter_idade(a) >= obter_freq_reproducao(a)


def eh_animal_faminto(a):
    """Verifica se o animal atingiu o estado de faminto

    Recebe um animal e devolve True se o mesmo atingiu um valor de fome igual ou superior 
    à sua frequência de alimentação. Caso contrário devolve False. 
    As presas devolvem sempre False.
    (animal --> booleano)
    """

    if eh_presa(a):
        return False

    return obter_fome(a) >= obter_freq_alimentacao(a)


def reproduz_animal(a):
    """Efetua a reprodução de um animal

    Recebe um animal e devolve um novo animal da mesma espécie com idade e fome igual a zero.
    O animal que deu origem a este (argumento) é modificado destrutivamente, alterando a sua
    idade para zero.
    """

    reset_idade(a)
    return cria_copia_animal(a)

# TAD prado - Operações Básicas


def cria_prado(d, r, a, p):
    """Construtor do TAD prado

    Recebe uma posicao (da montanha do canto inferior direito do prado), um tuplo com zero
    ou mais posições (de rochedos que não são montanhas dos limites exteriores do prado),
    um tuplo com um ou mais animais e um tuplo do mesmo tamanho do anterior com as posições
    correspondentes aos animais. Devolve uma nova variável do tipo prado com os atributos
    recebidos. Verifica a validade dos argumentos.
    (posicao x tuplo x tuplo x tuplo --> prado)
    """

    # Será preciso verificar que os rochedos no r não pertencem aos limites exteriores do prado?
    # E que os animais não estão em cima de rochedos?
    # E que os animais não estão em cima de outros animais?
    # Será preciso fazer isto no eh_prado?

    if not eh_posicao(d) or not isinstance(r, tuple) or not isinstance(a, tuple) or \
            not isinstance(p, tuple) or len(a) < 1 or len(a) != len(p) or \
            not all(map(lambda x: eh_posicao(x), r)) or \
            not all(map(lambda x, y: eh_animal(x) and eh_posicao(y), a, p)):

        raise ValueError('cria_prado: argumentos invalidos')

    return {'ult_pos': d, 'rochedos': r, 'animais': a, 'pos_animais': p}


def cria_copia_prado(m):
    return m.copy()


def obter_tamanho_x(m):
    return obter_pos_x(m['ult_pos']) + 1


def obter_tamanho_y(m):
    return obter_pos_y(m['ult_pos']) + 1


def obter_numero_predadores(m):
    return len([x for x in m['animais'] if eh_predador(x)])


def obter_numero_presas(m):
    return len([x for x in m['animais'] if eh_presa(x)])


def obter_posicao_animais(m):
    return ordenar_posicoes(m['pos_animais'])


# Função auxiliar
def obter_indice_pos_tuplo(t, p):
    for i in range(len(t)):
        if posicoes_iguais(t[i], p):
            return i

    return -1


def obter_animal(m, p):
    i = obter_indice_pos_tuplo(m['pos_animais'], p)
    if i == -1:
        return None
    return m['animais'][i]


def eliminar_animal(m, p):

    i = obter_indice_pos_tuplo(m['pos_animais'], p)
    if i == -1:
        return m

    # eliminar entradas do animal e posicao do animal
    m['pos_animais'] = m['pos_animais'][:i] + m['pos_animais'][i+1:]
    m['animais'] = m['animais'][:i] + m['animais'][i+1:]

    return m


def mover_animal(m, p1, p2):

    i = obter_indice_pos_tuplo(m['pos_animais'], p)
    if i == -1:
        return m
    m['pos_animais'] = m['pos_animais'][:i] + (p2,) + m['pos_animais'][i+1:]

    return m


def inserir_animal(m, a, p):
    m['animais'] += (a,)
    m['pos_animais'] += (p,)

    return m


def eh_prado(arg):
            # Propriedades do dicionário
            # Chaves do dicionário
            # Tipo dos valores

            # Tamanho dos valores que são tuplos
            # Tipo dos elementos dos valores que são tuplos

    return isinstance(arg, dict) and len(arg) == 4 and \
            'ult_pos' in arg and 'rochedos' in arg and 'animais' in arg and 'pos_animais' in arg \
            and eh_posicao(arg['ult_pos']) and isinstance(arg['rochedos'], tuple) and \
            isinstance(arg['animais'], tuple) and isinstance(arg['pos_animais'], tuple) and \
            len(arg['animais']) > 0 and len(arg['animais']) == len(arg['pos_animais']) and \
            all(map(lambda x: eh_posicao(x), arg['rochedos'])) and \
            all(map(lambda x,y: eh_animal(x) and eh_posicao(y), arg['animais'], arg['pos_animais']))


def eh_posicao_animal(m, p):
    return obter_indice_pos_tuplo(m['pos_animais'], p) != -1


def eh_posicao_obstaculo(m, p):
    # VERIFICAR SE PERTENCE AO EXTERIOR?
    return obter_indice_pos_tuplo(m['rochedos'], p) != -1


def eh_posicao_livre(m, p):
    return not (eh_posicao_animal(m, p) or eh_posicao_obstaculo(m, p))


# CHECK
def prados_iguais(p1, p2):
    # O prado é constituido pelos TADs posicao e animal, pelo que não pode ser simplesmente
    # comparado com ==
            # Posições dos rochedos

            # Animais e posições dos animais


    return eh_prado(p1) and eh_prado(p2) and \
            len(p1['rochedos']) == len(p2['rochedos']) and \
            all(map(lambda x, y: posicoes_iguais(x, y), p1['rochedos'], p2['rochedos'])) and \
            len(p1['animal']) == len(p2['animal']) and \
            all(map(lambda x, y: animais_iguais(x, y), p1['animais'], p2['animais'])) and \
            all(map(lambda x, y: posicoes_iguais(x, y), p1['pos_animais'], p2['pos_animais']))


def prado_para_str(m):
    tamanho_x = obter_tamanho_x(m)

    # topo
    res = '+' + '-'*(tamanho_x - 2) + '+\n'

    # linha por linha
    for y in range(1, obter_tamanho_y(m) - 1):
        res += '|'
        # coluna por coluna
        for x in range(1, tamanho_x - 1):
            pos = cria_posicao(x, y)
            
            # caso de haver rochedo
            if eh_posicao_obstaculo(m, pos):
                res += '@'
            else:
                # caso de ser um animal
                pos_animal = obter_indice_pos_tuplo(m['pos_animais'], pos)
                if pos_animal != -1:
                    res += animal_para_char(m['animais'][pos_animal])
                # caso estar livre
                else:
                    res += '.'
        res += '|\n'

    # fim
    res += '+' + '-'*(tamanho_x - 2) + '+'

    return res

# TAD prado - Funções de alto nível


def obter_valor_numerico(m, p):
    return obter_pos_y(p) * obter_tamanho_x(m) + obter_pos_x(p)


def obter_movimento(m, p):
    movs_possiveis = obter_posicoes_adjacentes(p)

    # predador
    if eh_predador(obter_animal(m, p)):
        for mp in movs_possiveis:
            if eh_presa(obter_animal(m, mp)):
                return mp
        # retirar obstáculos
        movs_possiveis = tuple(filter(lambda x: not eh_posicao_obstaculo(m, x), movs_possiveis))
    # presa
    else:    
        # retirar obstáculos/animais
        movs_possiveis = tuple(filter(lambda x: eh_posicao_livre(m, x), movs_possiveis))   

    return movs_possiveis[0]

