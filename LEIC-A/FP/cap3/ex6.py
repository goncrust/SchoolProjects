import ex5

def dias_mes(mes, ano):

    if mes == 'jan':
        return 31
    elif mes == 'fev':
        if ex5.bissexto(ano):
            return 29
        else:
            return 28
    elif mes == 'mar':
        return 31
    elif mes == 'abr':
        return 30
    elif mes == 'maio':
        return 31
    elif mes == 'jun':
        return 30
    elif mes == 'jul':
        return 31
    elif mes == 'ago':
        return 31
    elif mes == 'set':
        return 30
    elif mes == 'out':
        return 31
    elif mes == 'nov':
        return 30
    elif mes == 'dez':
        return 31
    else:
        raise ValueError("Mes nao existe")

