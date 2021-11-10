
def pertence(l, n):
    if l == []:
        return False
    elif l[0] == n:
        return True
    else:
        return pertence(l[1:], n)

