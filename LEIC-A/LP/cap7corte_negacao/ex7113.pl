
classe(N, C) :-
    N > 0, !,
    C = positivo.

classe(N, C) :-
    N < 0, !,
    C = negativo.

classe(_, C) :-
    C = zero.
