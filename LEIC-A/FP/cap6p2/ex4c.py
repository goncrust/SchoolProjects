

def acumula(lst, fn):
    if len(lst) == 1:
        return lst[0]
    else:
        return fn(acumula(lst[:-1], fn), lst[-1])


def acumula_alt(lst, fn):

    def aux(lst, fn, res):
        if lst == []:
            return res
        else:
            return aux(lst[1:], fn, fn(res, lst[0]))
    
    return aux(lst[1:], fn, lst[0])

