todos(_, []) :- !.

todos(Pred, [P | R]) :-
    T_c =.. [Pred, P],
    call(T_c),
    todos(Pred, R).
