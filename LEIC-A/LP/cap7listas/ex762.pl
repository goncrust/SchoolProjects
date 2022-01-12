insere_ordenado(E, [], [E]).
insere_ordenado(E, [P | R], [E | [P | R]]) :- P >= E.
insere_ordenado(E, [P | R], [P | R2]) :- P < E, insere_ordenado(E, R, R2).
