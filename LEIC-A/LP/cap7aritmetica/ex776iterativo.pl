soma_digitos(N, S) :- soma_digitos(N, S, 0).

soma_digitos(0, S, S).

soma_digitos(N, S, Ac) :-
    N =\= 0,
    NovoAc is Ac + (N mod 10),
    ProximoN is N//10,
    soma_digitos(ProximoN, S, NovoAc).
