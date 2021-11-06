
def soma_els_atomicos(t):
    if t == ():
        return 0
    else:
        if isinstance(t[0], (int, float)):
            return t[0] + soma_els_atomicos(t[1:])
        elif isinstance(t[0], tuple):
            return soma_els_atomicos(t[0]) + soma_els_atomicos(t[1:])

