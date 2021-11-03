import ex2b

def depois_rel(r1, r2):

    if ex2b.horas(r1) == ex2b.horas(r2):
        if ex2b.minutos(r1) == ex2b.minutos(r2):
            return ex2b.segs(r1) < ex2b.segs(r2)
        else:
            return ex2b.minutos(r1) < ex2b.minutos(r2)
    else: 
        return ex2b.horas(r1) < ex2b.horas(r2)

