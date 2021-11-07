
def soma_divisores(n):

    def aux(n, d, res):
        if d == 0:
            return res
        else:
            if n % d == 0:
                return aux(n, d-1, res+d)
            else:
                return aux(n, d-1, res)

    return aux(n, n, 0)
