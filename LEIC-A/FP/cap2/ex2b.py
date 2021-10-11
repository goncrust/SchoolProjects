dis = eval(input("Distancia percorrida (km): "))
min = eval(input("Intervalo de tempo (min): "))

secs = min * 60
dis_m = dis * 1000
velocidade = dis_m / secs

print("Velocidade media m/s:", velocidade)
