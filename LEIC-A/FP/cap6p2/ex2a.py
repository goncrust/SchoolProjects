
def piatorio(l_inf, l_sup, calc_prox):
    
    res = 1
    while l_inf <= l_sup:
        res *= l_inf
        l_inf = calc_prox(l_inf)

    return res

    
