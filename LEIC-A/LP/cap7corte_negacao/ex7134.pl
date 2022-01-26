intersecao([], _, []) :- !.

intersecao([P | R], L2, [P | RI]) :-
    member(P, L2), !,
    intersecao(R, L2, RI).

intersecao([_ | R], L2, I) :-
    intersecao(R, L2, I).
    
