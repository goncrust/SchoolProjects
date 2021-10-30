import random

def baralha(baralho):

    for i in range(len(baralho)):
        pos = random.randint(0, len(baralho) - 1)

        baralho[pos], baralho[i] = baralho[i], baralho[pos]
    
    return baralho
