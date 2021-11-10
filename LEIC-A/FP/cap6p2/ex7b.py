
def filtra_pares(n):

    ### Enunciado
    def misterio(num, p):
        if num == 0:
            return 0
        elif p(num % 10):
            return num % 10 + 10 * misterio(num // 10, p)
        else:
            return misterio(num//10, p)


    return misterio(n, lambda x: x % 2 == 0)

