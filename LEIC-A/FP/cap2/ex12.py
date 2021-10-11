x = eval(input("Qual o valor de x\n? "))
n = eval(input("Qual o valor de n\n? "))

res = 1

i = 1 
factorial = 1
while i <= n:
    factorial *= i 
    res += (x ** i) / factorial

    i += 1

print("O valor da soma e", res)
    
