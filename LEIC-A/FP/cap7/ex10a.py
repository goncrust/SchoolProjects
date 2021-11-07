
def perfeito(n):

    def aux(n, d, res):
        if d == 0:
            return res
        else:
            if n % d == 0:
                return aux(n, d-1, res+d)
            else:
                return aux(n, d-1, res)

    return n == aux(n, n//2, 0)
