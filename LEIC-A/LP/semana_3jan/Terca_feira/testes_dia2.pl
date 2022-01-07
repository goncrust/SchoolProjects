/*

Testar predicado do dia 2

*/

:- MensagemDia2 = [fail,hi,o,o, ola,sea],
    SeqFonte = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16],
    SeqAlvo = [o, d, e, y, ola, bye, fail, 5, a, j, 0, 45, sea, hello, hi, glory],
    descodificaDia2(SeqFonte, SeqAlvo, MensagemDia2, MensagemDecifrada),
    writeln('Predicado descodificaDia2 (dia 2):'),
    writeln(MensagemDecifrada),
    nl,
    writeln('Resultado correto (dia 2):'),
    writeln([7,15,1,1,5,13]).



