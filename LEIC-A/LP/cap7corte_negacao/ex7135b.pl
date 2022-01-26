disjuntas([], _).

disjuntas([P | R], L2) :-
    \+ member(P, L2),
    disjuntas(R, L2).
