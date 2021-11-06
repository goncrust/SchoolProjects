
def maior(l):

    def aux(l, maior):
        if l == []:
            return maior
        else:
            if l[0] > maior:
                return aux(l[1:], l[0])
            else:
                return aux(l[1:], maior)

    return aux(l[1:], l[0])
