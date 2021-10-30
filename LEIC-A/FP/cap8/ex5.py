
# genero (s), idade (i), altura (h), peso (p)
def metabolismo(d):
    
    res = {}
    for k in d:
        dados = d[k]

        s = dados[0]
        i = dados[1]
        h = dados[2]
        p = dados[3]

        if s == 'F':
            res[k] = 655 + 4.3*p + 4.7*h + 4.7*i
        elif s == 'M':
            res[k] = 66 + 6.3*p + 12.9*h + 6.8*i
            
    return res

