import math

running = True
continuar = 's'

while running == True:
    
    a = int(input("a: "))

    if a == 0:
        print("IMPOSSIVEL")
        continuar = input("Continuar(s/n)")
    
    if  continuar == 'n' and a == 0:
        break
    elif continuar != 'n' and a == 0:
        continue
    
    b = int(input("b: "))
    c = int(input("c: "))

    delta = ((b**2)-(4*a*c))

    if delta < 0:
        print("IMPOSSIVEL")
        continuar = input("Continuar(s/n)")

    if  continuar == 'n' and delta < 0:
        break
    elif continuar != 'n' and delta < 0:
        continue
    
    res = ((-b) + math.sqrt(delta))/(2*a)
    res1 = ((-b) - math.sqrt(delta))/(2*a)

    if delta == 0:
        print(res)
    else:
        print(res)
        print(res1)

    continuar = input("Continuar(s/n)")

    if  continuar == 'n':
        running = False

