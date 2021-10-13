
''' h -> dia da semana (0 = Sabado, 1 = Domingo, ...)
q -> dia do mes
m -> mes (3 = marco, 4 = abril, ..., 13 = janeiro, 14 = fevereiro)
K -> ano do seculo (ano mod 100)
J -> seculo (ano/100)
'''

# trata do janeiro e fevereiro 
# (que em vez de mes 1 e 2, sao representados como meses 13 e 14 no ano anterior)
def converter_mes_ano(m, ano):
    if not m > 2:
        ano -= 1
        m += 12

    return m, ano

def ano_seculo_do_ano(ano):
    J = ano // 100
    K = ano % 100

    return J, K

def dia_semana_portugues(h):

    if h == 0:
        return 'sabado'
    elif h == 1:
        return 'domingo'
    elif h == 2:
        return 'segundo'
    elif h == 3:
        return 'terca'
    elif h == 4:
        return 'quarta'
    elif h == 5:
        return 'quinta'
    elif h == 6:
        return 'sexta'

def dia_da_semana(q, m, ano):

    m, ano = converter_mes_ano(m, ano)

    J, K = ano_seculo_do_ano(ano)
    
    h = (q + int(13*(m+1)/5) + K + int(K/4) + int(J/4) - 2*J) % 7

    dia_semana_extenso = dia_semana_portugues(h)

    return dia_semana_extenso

