num = eval(input("Escreva um inteiro\n? "))

zeros = 0
anterior = 1
while num != 0:

    dig = num % 10
    if anterior == 0 and dig == 0:
        zeros += 1

    num //= 10
    anterior = dig

print(zeros)

