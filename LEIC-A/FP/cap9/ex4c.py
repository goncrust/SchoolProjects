import ex3b
import ex3d
import ex2d
import ex4b


def depois_ts(ts1, ts2):
    if ex3b.mesma_data(ex4b.data(ts1), ex4b.data(ts2)):
        return ex2d.depois_rel(ex4b.relogio(ts2), ex4b.relogio(ts1))
    return ex3d.data_anterior(ex4b.data(ts2), ex4b.data(ts1))
