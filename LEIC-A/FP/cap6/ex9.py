
def parte(l, n):

    def menores(l, n):
        if l == []:
            return []
        else:
            if l[0] < n:
                return [l[0]] + menores(l[1:], n)
            else:
                return menores(l[1:], n)

    def maiores(l, n):
        if l == []:
            return []
        else:
            if l[0] >= n:
                return [l[0]] + maiores(l[1:], n)
            else:
                return maiores(l[1:], n)

    return [menores(l, n), maiores(l, n)]

