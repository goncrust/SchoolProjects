inverte(N, I) :- inverte(N, I, 0).

inverte(0, I, I).

inverte(N, I, Ac) :-
    N =\= 0,
    NovoAc is Ac*10 + (N mod 10),
    NovoN is N//10,
    inverte(NovoN, I, NovoAc).
