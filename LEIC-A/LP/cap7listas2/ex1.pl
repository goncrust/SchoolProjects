menor(N1, N2) :- N1 < N2.

insere_ordenado(El, Lst1, Lst2) :-
    include(menor(El), Lst1, Maiores),
    exclude(menor(El), Lst1, Menores),
    append(Menores, [El | Maiores], Lst2).
