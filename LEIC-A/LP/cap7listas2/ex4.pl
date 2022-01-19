duplica_elementos(L1, L2) :-
    findall([E, E], member(E, L1), L3),
    append(L3, L2).
