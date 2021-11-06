
def sublistas(l):
    if l == []:
        return 0
    else:
        if isinstance(l[0], list):
            return 1 + sublistas(l[0]) + sublistas(l[1:])
        else:
            return sublistas(l[1:])
