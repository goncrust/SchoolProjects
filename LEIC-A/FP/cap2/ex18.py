num = eval(input("Escreva um inteiro\n? "))

zeros_temp = 0
res = 0
while num != 0:

    if num % 10 == 0:
        zeros_temp += 1 
    else:
        zeros_temp = 0

    if zeros_temp == 2:
        zeros_temp = 1
        res += 1

    num //= 10

print("O numero tem", res, "zeros seguidos")

    
        
