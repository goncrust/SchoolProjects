nota = eval(input("Nota (-1 para terminar): "))

positivas = 0
total = 0
while nota != -1:

    if nota >= 10:
        positivas += 1

    total += 1

    nota = eval(input("Nota (-1 para terminar): "))

percentagem = positivas / total * 100

print(positivas, "notas positivas\nPercentagem de notas positivas:", percentagem, "%")

