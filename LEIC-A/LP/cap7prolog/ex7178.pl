filtra_exc([], _, []).

filtra_exc([P1 | R1], Tst, L2) :-
    T_c =.. [Tst, P1],
    call(T_c), !,
    filtra_exc(R1, Tst, L2).

filtra_exc([P1 | R1], Tst, [P1 | R2]) :-
    filtra_exc(R1, Tst, R2).
    
