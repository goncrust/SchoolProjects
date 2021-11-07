import ex10a


def perfeitos_entre(n1, n2):
    if n1 > n2:
        return []
    else:
        if ex10a.perfeito(n1):
            return [n1] + perfeitos_entre(n1+1, n2)
        else:
            return perfeitos_entre(n1+1, n2)

