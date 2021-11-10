
def soma_fn(n, fn):

    res = 0
    for n in range(1, n+1):
        res += fn(n) 

    return res
