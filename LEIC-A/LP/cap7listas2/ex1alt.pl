insere_ordenado(El, Lst1, Lst2) :-
    findall(X, (member(X, Lst1), X < El), Menores),
    findall(X, (member(X, Lst1), X >= El), Maiores),
    append(Menores, [E | Maiores], Lst2).
