substitui_f(T_c, Novo_F, Novo_T_c) :-
    T_c =.. [_ | R],
    Novo_T_c =.. [Novo_F | R].
