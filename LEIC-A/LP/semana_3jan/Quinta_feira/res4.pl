% programaDia4(Matriz, Coordenadas, MensagemDescodificada)
programaDia4(_, [], []).
programaDia4(M, [[X, Y] | R], [P | R1]) :- nth1(X, M, L), nth1(Y, L, P), programaDia4(M, R, R1).
