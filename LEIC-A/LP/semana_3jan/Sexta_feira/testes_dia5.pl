/*

Testar predicado do dia 5
“peruca negra e boné” e “óculos e botas neve”. 
*/

:- PessoasBanco = [
    pessoa('Eugenio Lopes', ['camisa branca', oculos, 'peruca negra', bone]),
    pessoa('Tiburcio Ladrao', [oculos, 'peruca negra', bone]),
    pessoa('Antonio Maria', ['camisa preta', 'calcoes', 'peruca ruiva']),
    pessoa('Vania Silva', [bone, oculos, 'peruca negra']),
    pessoa('Octavio Mendonca', ['chapeu de palha', 'botas neve', calcas, 'camisa verde'])],
    CaracteristicasCriminoso = ['peruca negra', bone, oculos],
    encontraSuspeitos(PessoasBanco, CaracteristicasCriminoso, Suspeitos),
    nl, nl, writeln('Predicado encontraSuspeitos (dia 5):'),
    writeln(Suspeitos),
    nl,
    writeln('Resultado correto (dia 5):'),
    writeln(['Eugenio Lopes,', 'Tiburcio Ladrao', 'Vania Silva']).

