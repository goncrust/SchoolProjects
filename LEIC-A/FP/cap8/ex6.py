
def conta_palavras(s):

    palavra_atual = ''
    res = {}

    for c in s:
        if c != ' ':
            palavra_atual += c
        else:
            if palavra_atual not in res:
                res[palavra_atual] = 0

            res[palavra_atual] += 1
            palavra_atual = ''

    return res
