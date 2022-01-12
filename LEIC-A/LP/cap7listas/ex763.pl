junta_novo_aleatorio(L1, Linf, Lsup, L2) :- 
    random_between(Linf, Lsup, N),
    nao_membro(N, L1),
    insere_ordenado(N, L1, L2).
