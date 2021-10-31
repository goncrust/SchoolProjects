import ex10a

def soma_racionais(rac1, rac2):
    
    num = rac1['num'] * rac2['den'] + rac2['num'] * rac1['den']
    den = rac1['den'] * rac2['den']

    return ex10a.cria_racional(num, den)

