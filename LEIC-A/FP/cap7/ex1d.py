
def misterio(x, n):

    def aux(x, n, res):
        if n == 0:
            return res
        else:
            return aux(x, n-1, res + x*n)

    return aux(x, n, 0)

