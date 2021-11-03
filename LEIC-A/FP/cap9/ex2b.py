
def cria_rel(h, m, s):
    return [h, m, s]


def horas(r):
    return r[0]


def minutos(r):
    return r[1]


def segs(r):
    return r[2]


def eh_relogio(r):
    return isinstance(r, list) and len(r) == 3 and isinstance(r[0], int) and isinstance(r[1], int) \
            and isinstance(r[2], int)  and 0 <= r[0] <= 23 and 0 <= r[1] <= 59 and 0 <= r[2] <= 59


def eh_meia_noite(r):
    return r[0] == 0 and r[1] == 0 and r[2] == 0


def eh_meio_dia(r):
    return r[0] == 12 and r[1] == 0 and r[2] == 0


def mesmas_horas(r1, r2):
    return r1[0] == r2[0] and r1[1] == r2[1] and r1[2] == r2[2]

