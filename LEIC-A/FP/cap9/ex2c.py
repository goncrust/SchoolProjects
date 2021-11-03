import ex2b

def escreve_relogio(r):
    
    horas = str(ex2b.horas(r))
    mins = str(ex2b.minutos(r))
    segs = str(ex2b.segs(r))

    if len(horas) == 1:
        horas = '0' + horas
    if len(mins) == 1:
        mins = '0' + mins
    if len(segs) == 1:
        segs = '0' + segs

    return horas + ':' + mins + ':' + segs
