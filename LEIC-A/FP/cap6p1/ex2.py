
def junta_ordenadas(l1, l2):
    if l1 == []:
        return l2
    elif l2 == []:
        return l1
    else:
        if l1[0] < l2[0]:
            return [l1[0]] + junta_ordenadas(l1[1:], l2)
        else:
            return [l2[0]] + junta_ordenadas(l1, l2[1:])

