
def resumo_FP(notas):

    reprovados = 0
    aprovados = 0
    soma_notas_aprovados = 0
    for k in notas:
        if k < 10:
            reprovados += len(notas[k])
        else:
            n = len(notas[k]) 
            aprovados += n
            soma_notas_aprovados += k * n

    media_aprovados = soma_notas_aprovados / aprovados
    
    return (media_aprovados, reprovados)

