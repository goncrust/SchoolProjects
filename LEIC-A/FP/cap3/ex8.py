
def serie_geom(r, n):

    if n < 0:
        raise ValueError("serie_geom: argumento incorreto")

    if n == 0:
        return 1
    else:
        res = r ** n + serie_geom(r, n-1)
        return res

