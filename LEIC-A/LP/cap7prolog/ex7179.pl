acumula([P | R], Op, Res) :-
    acumula(R, Op, P, Res).

acumula([], _, R, R).

acumula([P | R], Op, Ac, Res) :-
    T_c =.. [Op, Ac, P, Novo_Ac],
    call(T_c),
    acumula(R, Op, Novo_Ac, Res).
