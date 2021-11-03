import ex3b


def data_anterior(dt1, dt2):

    if ex3b.mesma_data(dt1, dt2):
        return False

    if ex3b.ano(dt1) == ex3b.ano(dt2):
        if ex3b.mes(dt1) == ex3b.mes(dt2):
            return ex3b.dia(dt1) < ex3b.dia(dt2)
        return ex3b.mes(dt1) < ex3b.mes(dt2)
    return ex3b.ano(dt1) < ex3b.ano(dt2)

