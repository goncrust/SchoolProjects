quantos(_, [], 0) :- !.

quantos(Pred, [P | R], N) :-
    T_c =.. [Pred, P],
    call(T_c), !,
    quantos(Pred, R, N1),
    N is N1 + 1.

quantos(Pred, [_ | R], N) :-
    quantos(Pred, R, N).
