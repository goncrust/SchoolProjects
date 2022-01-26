transforma(_, [], []) :- !.

transforma(Tr, [P1 | R1], [P2 | R2]) :-
    T_c =.. [Tr, P1, P2],
    call(T_c),
    transforma(Tr, R1, R2).
