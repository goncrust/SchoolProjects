soma_digitos(0, 0).

soma_digitos(N, S) :-
    N =\= 0,
    ProximoN is N//10,
    soma_digitos(ProximoN, S1),
    S is (N mod 10) + S1.
