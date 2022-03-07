substitui_arg(T_c, Arg, Novo_Arg, Novo_T_c) :-
    T_c =.. [P | R],
    substitui_lista(R, Arg, Novo_Arg, Nova_Lista),
    Novo_T_c =.. [P | Nova_Lista].

substitui_lista([], _, _, []).

substitui_lista([Ant | R], Ant, Novo, [Novo | R2]) :-
    substitui_lista(R, Ant, Novo, R2), !.

substitui_lista([P | R], Ant, Novo, [P | R2]) :-
    substitui_lista(R, Ant, Novo, R2).
