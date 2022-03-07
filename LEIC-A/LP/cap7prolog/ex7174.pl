algum(Pred, [P | _]) :-
    T_c =.. [Pred, P],
    call(T_c), !.

algum(Pred, [_ | R]) :-
    algum(Pred, R).
