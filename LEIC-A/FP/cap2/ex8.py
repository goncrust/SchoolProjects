
while True:
    secs = eval(input("Escreva um numero de segundos\n(um numero negativo para terminar)\n? "))
    if secs < 0:
        break

    days = secs / 60 / 60 / 24
    print("O numero de dias correspondente e", days)
