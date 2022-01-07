/*

Testar predicados do dia 3

*/

:- SeqFonte = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
   SeqAlvo = [o, d, e, y, ola, bye, fail, 5, a, j],
   programa1Dia3(SeqFonte, SeqAlvo, ListaParesCodigo),
   nl, nl, writeln('Predicado programa1Dia3 (dia 3):'),
   writeln(ListaParesCodigo),
   nl,
   writeln('Resultado correto programa1Dia3 (dia 3):'),
   writeln([par(1,o),par(2,d),par(3,e),par(4,y),par(5,ola),par(6,bye),par(7,fail),par(8,5),par(9,a),par(10,j)]),
    MensagemDia2 = [ola,d, y,hello, fail,e, fail,glory, ola,e,bye,a],
    programa2Dia3(ListaParesCodigo, MensagemDia2, MensagemDescodificada),
    nl, nl, writeln('Predicado programa2Dia3 (dia 3):'),
    writeln(MensagemDescodificada),
    nl,
    writeln('Resultado correto programa2Dia3 (dia 3):'),
    writeln([5,2,4,7,3,7,5,3,6,9]).

