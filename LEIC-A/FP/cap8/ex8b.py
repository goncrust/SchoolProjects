
def soma_esparsa(d1, d2):

    for e in d2:
        if e in d1:
            d1[e] += d2[e]
        else:
            d1[e] = d2[e]

    return d1
