aplica_op(Op, Val1, Val2, R) :-
    L =.. [Op, Val1, Val2],
    R is L.
