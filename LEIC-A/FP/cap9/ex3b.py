

def ano_bissexto(a):
    return (a % 4 == 0 and a % 100 != 0) or a % 400 == 0


def argumentos_validos(d, m, a):

    if not isinstance(d, int) or d < 1 or not isinstance(m , int) or m < 1 or m > 12 \
            or not isinstance(a, int):
                return False

    if m in (1, 3, 5, 7, 8, 10, 12) and d > 31:
        return False
    elif m == 2:
        if ano_bissexto(a) and d > 28:
            return False
        elif not ano_bissexto(a) and d > 27:
            return False
    else:
        if d > 30:
            return False

    return True


def cria_data(d, m, a):

    if argumentos_validos(d, m, a):
        return {'dia': d, 'mes': m, 'ano': a}
    else:
        raise ValueError('cria_data: argumentos invalidos')


def dia(dt):
    return dt['dia']


def mes(dt):
    return dt['mes']


def ano(dt):
    return dt['ano']


def eh_data(arg):
    if isinstance(arg, dict) and len(arg) == 3 and 'dia' in arg and 'mes' in arg and 'ano' in arg:
        return argumentos_validos(arg['dia'], arg['mes'], arg['ano'])

    return False


def mesma_data(dt1, dt2):
    return dt1['dia'] == dt2['dia'] and dt1['mes'] == dt2['mes'] and dt1['ano'] == dt2['ano']

