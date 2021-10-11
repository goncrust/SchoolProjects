num = eval(input("Escreva um numero\n-> "))
res = num

inverso = 0
while num != 0:
    res *= 10
    res += num % 10

    num //= 10

print(res)
