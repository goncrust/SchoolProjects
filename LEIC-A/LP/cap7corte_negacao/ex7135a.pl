disjuntas([], _).

disjuntas([P | _], L2) :-
    member(P, L2), !, fail.

disjuntas([_ | R], L2) :-
    disjuntas(R, L2).
