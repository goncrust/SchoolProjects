
def cria_rac(n, d):
    
    if not isinstance(n, int) or not isinstance(d, int) or d < 1:
        raise ValueError('cria_rac: valores incorretos')

    return {'num': num, 'den', den}


def num(r):
    
    return r['num']


def den(r):

    return r['den']


def eh_racional(arg):

    return isinstance(arg, dict) and len(arg) == 2 and 'num' in arg and 'den' in arg and \
            isinstance(arg['num'], int) and isinstance(arg['den'], int) and arg['den'] > 0

def eh_racional_zero(r):

    return r['num'] / r['den'] == 0

def rac_iguais(r1, r2):

    return r1['num'] / r1['den'] == r2['num'] / r2['den']
