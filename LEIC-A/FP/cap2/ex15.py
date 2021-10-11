
res = 0
while True:
    dig = eval(input("Digito (-1 para terminar): "))

    # caso -1 seja introduzido
    if dig == -1:
        break

    res *= 10
    res += dig

print("Resultado:", res)
