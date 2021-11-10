
### Enunciado

def eh_primo(n):
    if n == 1:
        return False
    else:
        for i in range(2, n):
            if n % i == 0:
                return False
        return True

### Exercicio

def nao_primos(n, f):
    return [y for y in range(n) if y not in list(filter(f, [x for x in range(n)]))]

## or

def nao_primos_alt(n, f):
    return [x for x in range(n) if not f(x)]

###

#print(nao_primos(10, eh_primo))
print(nao_primos_alt(10, eh_primo))
