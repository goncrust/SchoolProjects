
def espelho(n):

    def aux(n, res):
        if n == 0:
            return res
        else:
            return aux(n//10, res*10 + n%10)

    return aux(n, 0)

