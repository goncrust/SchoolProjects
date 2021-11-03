
def vetor(x, y):
    return (x, y)


def abcissa(v):
    return v[0]


def ordenada(v):
    return v[1]


def eh_vetor(arg):
    return isinstance(arg, tuple) and len(arg) == 2 and isinstance(arg[0], int) and isinstance(arg[1], int)


def eh_vetor_nulo(v):
    return v[0] == 0 and v[1] == 0


def vetores_iguais(v1, v2):
    return v1[0] == v2[0] and v1[1] == v2[1]

