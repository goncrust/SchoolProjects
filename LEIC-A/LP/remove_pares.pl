par(N) :-
    N mod 2 =:= 0.

remove_pares(L1, L2) :-
    exclude(par, L1, L2).
