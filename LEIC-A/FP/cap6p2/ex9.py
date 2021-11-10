import ex8
from functools import reduce

def produto_digitos(n, pred):
    return reduce(lambda x, y: x * y, filter(pred, ex8.lista_digitos(n))) 

