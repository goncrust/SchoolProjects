digitos_N_1(N, I) :- N > 0, N < 10, digitos_N_1(N, I, 0).

digitos_N_1(0, I, I).

digitos_N_1(N, I, Ac) :-
    N =\= 0,
    NovoAc is Ac*10+N,
    NovoN is N-1,
    digitos_N_1(NovoN, I, NovoAc).
