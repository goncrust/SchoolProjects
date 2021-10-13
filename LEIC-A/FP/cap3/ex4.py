import ex3

def area_coroa(r1, r2):
    if r1 > r2:
        raise ValueError("Raio interior maior do que raio exterior")

    area = ex3.area_circulo(r2) - ex3.area_circulo(r1)

    return area

