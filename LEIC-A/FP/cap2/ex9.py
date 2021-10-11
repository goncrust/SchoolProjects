dig = 0
res = 0

while True:
    dig = eval(input("Escreva um digito\n(-1 para terminal)\n? "))

    if dig == -1:
        break

    res *= 10
    res += dig

print("O numero e:", res)
