% programa1Dia3(SequenciaFonte, SequenciaAlvo, ListaParesCodigo)

programa1Dia3([], [], []).
programa1Dia3([P | R], [P1 | R1], [par(P, P1) | R2]) :- programa1Dia3(R, R1, R2).

% programa2Dia3(ListaParesCodigo, MensagemCodificada, MensagemDescodificada)

programa2Dia3(_, [], []).
programa2Dia3(Pares, [P | R], [P1 | R1]) :- nth1(_, Pares, par(P1, P)), programa2Dia3(Pares, R, R1).
programa2Dia3(Pares, [_ | R], L) :- programa2Dia3(Pares, R, L).

