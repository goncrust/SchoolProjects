n_aleatorios(N, Linf, Lsup, L1) :- n_aleatorios(N, Linf, Lsup, [], L1).
n_aleatorios(0, _, _, L1, L1).
n_aleatorios(N, Linf, Lsup, Ac, L1) :-
    Nmenos1 is N-1,
    junta_novo_aleatorio(Ac, Linf, Lsup, L2),
    n_aleatorios(Nmenos1, Linf, Lsup, L2, L1).
