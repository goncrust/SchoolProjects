filtra_inc([], _, []) :- !.

filtra_inc([P1 | R1], Tst, [P1 | R2]) :-
    T_c =.. [Tst, P1],
    call(T_c), !,
    filtra_inc(R1, Tst, R2).

filtra_inc([_ | R1], Tst, L2) :-
    filtra_inc(R1, Tst, L2).
    
