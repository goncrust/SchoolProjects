
def cria_racional(num, den):

    if den == 0:
        raise ValueError("o denominador nao pode ser 0")

    if not isinstance(den, int) or not isinstance(num, int):
        raise ValueError("os numeros devem ser inteiros")


    case = True

    while case:
        case = False
        for i in range(2, den+1):
            if den % i == 0 and num % i == 0:
                den = den // i
                num = num//i
                case = True
                break

    return {'num': num, 'den': den}

