
def subtrai(l1, l2):
    if l1 == []:
        return []
    else:
        if l1[0] in l2:
            return subtrai(l1[1:], l2)
        else:
            return [l1[0]] + subtrai(l1[1:], l2)

