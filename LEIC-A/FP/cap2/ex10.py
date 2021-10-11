num = eval(input("Escreva um inteiro\n? "))

res = 0
house = 0

while num != 0:
    dig = num % 10
    if dig % 2 != 0:
        dig *= (10 ** house)
        res += dig
        house += 1
    num //= 10

print("Resultado:", res)

