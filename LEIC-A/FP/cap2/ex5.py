from math import sqrt, pow

a = eval(input("a: "))
b = eval(input("b: "))
c = eval(input("c: "))
d = eval(input("d: "))
e = eval(input("e: "))

media = (a + b + c + d + e)/5

meio = pow(a - media, 2) + pow(b - media, 2) + pow(c - media, 2) + pow(d - media, 2) + pow(e - media, 2)
desvio = sqrt((1/4) * meio)

print("Media:", media, "\nDesvio:", desvio)
