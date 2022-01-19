junta_novo_aleatorio(L1, Linf, Lsup, L2) :-
    random_between(Linf, Lsup, N),
    \+ member(N, L1),
    insere_ordenado(N, L1, L2).
