import ex3b
import ex3d


def idade(nascimento, dt):
    if ex3d.data_anterior(dt, nascimento):
        raise ValueError('idade: a pessoa ainda nao nasceu')

    dia = ex3b.dia(nascimento)
    mes = ex3b.mes(nascimento)
    ano = ex3b.ano(dt)
    diferenca_anos = ano - ex3b.ano(nascimento)

    if ex3d.data_anterior(dt, ex3b.cria_data(dia, mes, ano)):
        diferenca_anos -= 1

    return diferenca_anos

