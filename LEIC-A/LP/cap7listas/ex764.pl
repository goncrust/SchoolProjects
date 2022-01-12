n_aleatorios(0, _, _, _).
n_aleatorios(N, Linf, Lsup, L) :- 
    N > 0,
    Nmenos1 is N-1,
    n_aleatorios(Nmenos1, Linf, Lsup, L1),
    junta_novo_aleatorio(L1, Linf, Lsup, L).
