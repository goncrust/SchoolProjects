import ex2b
import ex3b


def cria_time_stamp(dt, rel):
    if ex3b.eh_data(dt) and ex2b.eh_relogio(rel):
        return (dt, rel)
    
    raise ValueError('cria_time_stamp: parametros invalidos')


def data(ts):
    return ts[0]


def relogio(ts):
    return ts[1]


def eh_time_stamp(ts):
    return isinstance(ts, tuple) and len(ts) == 2 and ex3b.eh_data(ts[0]) and ex2b.eh_relogio(ts[1])


def mesmo_time_stamp(ts1, ts2):
    return ex3b.mesma_data(ts1[0], ts2[0]) and ex2b.mesmas_horas(ts1[1], ts2[1])

