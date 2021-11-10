
def filtra(lst, tst):

    if lst == []:
        return []
    else:
        if tst(lst[0]):
            return [lst[0]] + filtra(lst[1:], tst)
        else:
            return filtra(lst[1:], tst)

