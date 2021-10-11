num = eval(input("Escreva um inteiro positivo\n? "))

res = 0

while num != 0:
    res *= 10
    res += num % 10

    num //= 10

print("Resultado:", res)
