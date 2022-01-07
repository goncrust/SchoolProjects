% descodificaDia2(SequenciaFonte, SequenciaAlvo, MensagemCodificada, MensagemDescodificada)

descodificaDia2(_, _, [], []).
descodificaDia2(F, A, [P | R], [P2 | R2]) :- nth1(I, A, P), nth1(I, F, P2), descodificaDia2(F, A, R, R2).

