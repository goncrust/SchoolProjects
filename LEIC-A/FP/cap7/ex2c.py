
def quadrado(n):

    res = 0
    while n != 0:
        res += n + n - 1
        n -= 1

    return res

