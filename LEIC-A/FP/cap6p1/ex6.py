
def inverte(l):
    if l == []:
        return []
    else:
        return inverte(l[1:]) + [l[0]]

