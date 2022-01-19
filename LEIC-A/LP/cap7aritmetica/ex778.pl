triangular(N) :- triangular(N, 1, 2).

triangular(N, N, _).

triangular(N, Ac, Cnum) :-
    N > Ac,
    NAc is Ac + Cnum,
    NCnum is Cnum + 1,
    triangular(N, NAc, NCnum).

