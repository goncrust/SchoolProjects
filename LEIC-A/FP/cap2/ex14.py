num = eval(input("Inteiro: "))

res = 0
while num != 0:
    res += num % 10
    num //= 10

print("Resultado:", res)
