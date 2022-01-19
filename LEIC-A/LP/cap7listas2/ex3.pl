repete_el(El, N, L) :-
    length(L, N),
    maplist(=(El), L).
