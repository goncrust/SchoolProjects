import ex3b


def escreve_data(dt):

    dia = str(ex3b.dia(dt))
    mes = str(ex3b.mes(dt))
    ano = ex3b.ano(dt)

    if ano < 0:
        ano = str(-ano) + ' ' + 'AC'
    else:
        ano = str(ano)

    if len(dia) == 1:
        dia = '0' + dia
    if len(mes) == 1:
        mes = '0' + mes


    return dia + '/' + mes + '/' + ano

