
def apenas_digitos_impares(n):
    if n == 0:
        return 0
    else:
        if (n % 10) % 2 == 0:
            return apenas_digitos_impares(n//10)
        else:
            return apenas_digitos_impares(n//10)*10 + n%10

