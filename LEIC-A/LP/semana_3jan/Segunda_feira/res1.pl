% eliminaNumeros(Lista, ListaSemNumeros)

eliminaNumeros([], []).
eliminaNumeros([P | R], L) :- number(P), eliminaNumeros(R, L).
eliminaNumeros([P | R], [P | R2]) :- eliminaNumeros(R, R2).
