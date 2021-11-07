import ex3c


def eh_capicua(n):

    def aux(n, t):
        if t <= 1:
            return True
        elif n[0] != n[t - 1]:
            return False
        else:
            return aux(n[1:t-1], t-2)

    return aux(str(n), ex3c.numero_digitos(n))


def eh_capicua_alt(n):

    def aux(n, res):
        if n == 0:
            return res
        else:
            return aux(n//10, res*10 + n%10)

    return aux(n, 0) == n

