
def baralho():
    res = []
    
    naipes = ('espadas', 'copas', 'ouros', 'paus')
    valores = ('A', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K')

    for n in naipes:
        for v in valores:
            res.append({'np': n, 'vlr': v})

    return res

