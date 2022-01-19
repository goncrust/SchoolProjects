num_occ(L, E, N) :-
    findall(X, (member(X, L), X == E), L2),
    length(L2, N).
