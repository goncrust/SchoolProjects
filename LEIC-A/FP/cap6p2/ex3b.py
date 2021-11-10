
def soma_fn(n, f):
    if n == 0:
        return 0
    else:
        return f(n) + soma_fn(n-1, f)

