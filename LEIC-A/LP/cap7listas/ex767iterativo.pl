duplica_elementos(L1, L2) :- duplica_elementos(L1, [], L2).
duplica_elementos([], L2, L2).
duplica_elementos([P | R], Ac, L2) :- 
    adicionar(Ac, P, N),
    adicionar(N, P, N2),
    duplica_elementos(R, N2, L2).

adicionar([], E, [E]).
adicionar([P | R], E, [P | R2]) :- adicionar(R, E, R2).
