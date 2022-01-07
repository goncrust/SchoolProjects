% encontraSuspeitos(PessoasBanco, CaracteristicasCriminoso, Suspeitos)
encontraSuspeitos([], _, []).
encontraSuspeitos([pessoa(N, C) | R], Caracteristicas, [N | R1]) :-
    subset(Caracteristicas, C),
    encontraSuspeitos(R, Caracteristicas, R1).
encontraSuspeitos([_ | R], C, L) :- encontraSuspeitos(R, C, L).
