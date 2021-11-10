
def transforma(lst, fn):
    if lst == []:
        return []
    else:
        return [fn(lst[0])] + transforma(lst[1:], fn)

