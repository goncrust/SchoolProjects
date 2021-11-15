

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

    return isinstance(arg, tuple) and len(arg) == 2 and isinstance(arg[0], int) and isinstance(arg[1], int) \
            and arg[0] >= 0 and arg[1] >= 0


def posicoes_iguais(p1, p2):
    """Verifica se as posicoes são iguais

    Recebe dois TAD posicao e devolve True apenas se ambos representam a mesma posicao.
    Caso contrário devolve False.
    (posicao x posicao --> booleano)
    """

    return eh_posicao(p1) and eh_posicao(p2) and p1[0] == p2[0] and p1[1] == p2[1]


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
    recebidas ordenadas de acordo com a ordem de leitura do prado. Esta função é baseada
    no algoritmo Bubble Sort.
    (tuplo --> tuplo)
    """

    def primeiro_no_prado(p1, p2):
        """Verifica se a posicao esta primeiro na ordem de leitura do prado

        Recebe dois TADs posicao e devolve True se a primeira está em primeiro lugar
        na ordem de leitura do prado, em relação à segunda. Caso a segunda esteja primeiro,
        devolve False
        """

        if posicoes_iguais(p1, p2):
            return False

        if obter_pos_y(p1) == obter_pos_y(p2):
            return obter_pos_x(p1) < obter_pos_x(p2)
        return obter_pos_y(p1) < obter_pos_y(p2)

    # Mais eficiente transformar o tuplo em lista
    l = list(t)
    alteracao = True
    for i in range(len(l)-1, 0, -1):
        alteracao = False
        for j in range(1, i+1):
            if primeiro_no_prado(l[j], l[j-1]):
                l[j], l[j-1] = l[j-1], l[j]
                alteracao = True

        if not alteracao:
            break

    return tuple(l)

