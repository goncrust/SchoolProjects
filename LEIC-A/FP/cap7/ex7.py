
def calc_soma(x, n):
    
    def aux(x, n, i, x_original):
        if n == 0:
            return 1
        else:
            return x/i + aux(x*x_original, n-1, i*(i+1), x_original)

    return aux(x, n, 1, x)

