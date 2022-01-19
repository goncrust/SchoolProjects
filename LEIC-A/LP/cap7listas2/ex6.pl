maior_sub(N, S, E, R) :-
    N < E,
    R = S.

maior_sub(N, _, E, R) :-
    N >= E,
    R = E.

substitui_maiores_N(N, Subst, L1, L2) :-
    maplist(maior_sub(N, Subst), L1, L2).
