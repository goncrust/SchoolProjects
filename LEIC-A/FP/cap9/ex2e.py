import ex2b
import ex2d

def dif_segs(r1, r2):

    if ex2b.mesmas_horas(r1, r2):
        return 0

    if ex2d.depois_rel(r2, r1):
        raise ValueError('dif_segs: primeiro arg posterior ao segundo')

    dif_horas = ex2b.horas(r2) - ex2b.horas(r1)
    dif_mins = ex2b.minutos(r2) - ex2b.minutos(r1)
    dif_segs = ex2b.segs(r2) - ex2b.segs(r1)

    res = dif_horas * 60 * 60 + dif_mins * 60 + dif_segs
    return res

