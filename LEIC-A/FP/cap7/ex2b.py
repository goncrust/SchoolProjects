
def quadrado(n):

    def aux(n, res):
        if n == 0:
            return res
        else:
            return aux(n - 1, res + n+n-1)

    return aux(n, 0)

