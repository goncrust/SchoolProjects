comp_maior_lista(L, C) :- comp_maior_lista(L, 0, C).
comp_maior_lista([], C, C).
comp_maior_lista([P | R], Ac, C) :- 
    length(P, N), N > Ac,
    comp_maior_lista(R, N, C).
comp_maior_lista([P | R], Ac, C) :- 
    length(P, N), N =< Ac,
    comp_maior_lista(R, Ac, C).
