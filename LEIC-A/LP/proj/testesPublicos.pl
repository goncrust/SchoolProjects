%--------------------------------
% extrai_ilhas_linha (P1)
%--------------------------------

% Teste 1 - Publico
% Predicado extrai_ilhas_linha (P1)
% Cotacao total: 1
% Cotacao parcial: 0

:- extrai_ilhas_linha(1, [2, 0, 0, 0, 0, 0, 3], Ilhas), writeln(Ilhas); writeln(false).
% output: [ilha(2,(1,1)),ilha(3,(1,7))]
:- extrai_ilhas_linha(5, [0, 0, 3, 0, 0, 3, 0], Ilhas), writeln(Ilhas); writeln(false).
% output: [ilha(3,(5,3)),ilha(3,(5,6))]
:- extrai_ilhas_linha(5, [0, 0, 0, 0, 0, 0, 0], Ilhas), writeln(Ilhas); writeln(false).
% output: [].

%--------------------------------
% ilhas (P2)
%--------------------------------

% Teste 3 - Publico
% Predicado ilhas (P2)
% Cotacao total: 1
% Cotacao parcial: 0

:- Puz = [[0,0,2,0,0],[0,0,0,0,0],[1,0,6,0,1],[0,0,0,0,0],[0,0,2,0,0]], ilhas(Puz, Ilhas), writeln(Ilhas); writeln(false).
% output: [ilha(2,(1,3)),ilha(1,(3,1)),ilha(6,(3,3)),ilha(1,(3,5)),ilha(2,(5,3))]
:- Puz = [[1,0,4,0,1],[0,0,0,0,0],[0,0,2,0,0]], ilhas(Puz, Ilhas), writeln(Ilhas); writeln(false).
% output: [ilha(1,(1,1)),ilha(4,(1,3)),ilha(1,(1,5)),ilha(2,(3,3))]

% Teste 5 - Publico
% Predicado ilhas (P2)
% Cotacao total: 1
% Cotacao parcial: 0

:- Puz = [[0,4,0,0,0,0,0,0,0,3],[0,0,3,0,0,0,3,0,1,0],[0,2,0,0,0,0,0,0,0,0],[3,0,4,0,0,0,0,0,0,4],[0,0,0,0,0,0,1,0,0,0],[0,0,0,1,0,0,0,0,0,0],[3,0,0,0,0,0,0,0,0,0],[0,0,0,3,0,0,5,0,0,4],[0,0,0,0,0,0,0,0,0,0],[2,0,4,0,2,0,1,0,0,1]], ilhas(Puz, Ilhas), writeln(Ilhas); writeln(false).
% output: [ilha(4,(1,2)),ilha(3,(1,10)),ilha(3,(2,3)),ilha(3,(2,7)),ilha(1,(2,9)),ilha(2,(3,2)),ilha(3,(4,1)),ilha(4,(4,3)),ilha(4,(4,10)),ilha(1,(5,7)),ilha(1,(6,4)),ilha(3,(7,1)),ilha(3,(8,4)),ilha(5,(8,7)),ilha(4,(8,10)),ilha(2,(10,1)),ilha(4,(10,3)),ilha(2,(10,5)),ilha(1,(10,7)),ilha(1,(10,10))]

%--------------------------------
% vizinhas (P3)
%--------------------------------

% Teste 7 - Publico
% Predicado vizinhas (P3)
% Cotacao total: 2
% Cotacao parcial: 0

:- Ilhas = [ilha(2,(1,3)),ilha(1,(3,1)),ilha(6,(3,3)),ilha(1,(3,5)),ilha(2,(5,3))], vizinhas(Ilhas, ilha(1, (3, 1)), Vizinhas), writeln(Vizinhas); writeln(false).
% output: [ilha(6,(3,3))]
:- Ilhas = [ilha(2,(1,3)),ilha(1,(3,1)),ilha(6,(3,3)),ilha(1,(3,5)),ilha(2,(5,3))], vizinhas(Ilhas, ilha(6, (3, 3)), Vizinhas), writeln(Vizinhas); writeln(false).
% output: [ilha(2,(1,3)),ilha(1,(3,1)),ilha(1,(3,5)),ilha(2,(5,3))]

% Teste 9 - Publico
% Predicado vizinhas (P3)
% Cotacao total: 2
% Cotacao parcial: 0

:- Ilhas = [ilha(1,(1,1)),ilha(4,(1,3)),ilha(1,(1,5)),ilha(2,(3,3))], vizinhas(Ilhas, ilha(4, (1, 3)), Vizinhas), writeln(Vizinhas); writeln(false). 
% output: [ilha(1,(1,1)),ilha(1,(1,5)),ilha(2,(3,3))]

%--------------------------------
% estado (P4)
%--------------------------------

% Teste 11 - Publico
% Predicado estado (P4)
% Cotacao total: 1
% Cotacao parcial: 0

:- Ilhas = [ilha(2,(1,3)),ilha(1,(3,1)),ilha(6,(3,3)),ilha(1,(3,5)),ilha(2,(5,3))], estado(Ilhas, Estado), writeln(Estado); writeln(false).  
% output: [[ilha(2,(1,3)),[ilha(6,(3,3))],[]],[ilha(1,(3,1)),[ilha(6,(3,3))],[]],[ilha(6,(3,3)),[ilha(2,(1,3)),ilha(1,(3,1)),ilha(1,(3,5)),ilha(2,(5,3))],[]],[ilha(1,(3,5)),[ilha(6,(3,3))],[]],[ilha(2,(5,3)),[ilha(6,(3,3))],[]]]
:- Ilhas = [ilha(1,(1,1)),ilha(4,(1,3)),ilha(1,(1,5)),ilha(2,(3,3))], estado(Ilhas, Estado), writeln(Estado); writeln(false). 
% output: [[ilha(1,(1,1)),[ilha(4,(1,3))],[]],[ilha(4,(1,3)),[ilha(1,(1,1)),ilha(1,(1,5)),ilha(2,(3,3))],[]],[ilha(1,(1,5)),[ilha(4,(1,3))],[]],[ilha(2,(3,3)),[ilha(4,(1,3))],[]]]
:- Ilhas = [ilha(4,(1,2)),ilha(3,(1,10)),ilha(3,(2,3)),ilha(3,(2,7)),ilha(1,(2,9)),ilha(2,(3,2)),ilha(3,(4,1)),ilha(4,(4,3)),ilha(4,(4,10)),ilha(1,(5,7)),ilha(1,(6,4)),ilha(3,(7,1)),ilha(3,(8,4)),ilha(5,(8,7)),ilha(4,(8,10)),ilha(2,(10,1)),ilha(4,(10,3)),ilha(2,(10,5)),ilha(1,(10,7)),ilha(1,(10,10))], estado(Ilhas, Estado), writeln(Estado); writeln(false).  
% output: [[ilha(4,(1,2)),[ilha(3,(1,10)),ilha(2,(3,2))],[]],[ilha(3,(1,10)),[ilha(4,(1,2)),ilha(4,(4,10))],[]],[ilha(3,(2,3)),[ilha(3,(2,7)),ilha(4,(4,3))],[]],[ilha(3,(2,7)),[ilha(3,(2,3)),ilha(1,(2,9)),ilha(1,(5,7))],[]],[ilha(1,(2,9)),[ilha(3,(2,7))],[]],[ilha(2,(3,2)),[ilha(4,(1,2))],[]],[ilha(3,(4,1)),[ilha(4,(4,3)),ilha(3,(7,1))],[]],[ilha(4,(4,3)),[ilha(3,(2,3)),ilha(3,(4,1)),ilha(4,(4,10)),ilha(4,(10,3))],[]],[ilha(4,(4,10)),[ilha(3,(1,10)),ilha(4,(4,3)),ilha(4,(8,10))],[]],[ilha(1,(5,7)),[ilha(3,(2,7)),ilha(5,(8,7))],[]],[ilha(1,(6,4)),[ilha(3,(8,4))],[]],[ilha(3,(7,1)),[ilha(3,(4,1)),ilha(2,(10,1))],[]],[ilha(3,(8,4)),[ilha(1,(6,4)),ilha(5,(8,7))],[]],[ilha(5,(8,7)),[ilha(1,(5,7)),ilha(3,(8,4)),ilha(4,(8,10)),ilha(1,(10,7))],[]],[ilha(4,(8,10)),[ilha(4,(4,10)),ilha(5,(8,7)),ilha(1,(10,10))],[]],[ilha(2,(10,1)),[ilha(3,(7,1)),ilha(4,(10,3))],[]],[ilha(4,(10,3)),[ilha(4,(4,3)),ilha(2,(10,1)),ilha(2,(10,5))],[]],[ilha(2,(10,5)),[ilha(4,(10,3)),ilha(1,(10,7))],[]],[ilha(1,(10,7)),[ilha(5,(8,7)),ilha(2,(10,5)),ilha(1,(10,10))],[]],[ilha(1,(10,10)),[ilha(4,(8,10)),ilha(1,(10,7))],[]]]

%--------------------------------
% posicoes_entre (P5)
%--------------------------------

% Teste 13 - Publico
% Predicado posicoes_entre (P5)
% Cotacao total: 1
% Cotacao parcial: 0

:- posicoes_entre((1,2), (1,6), Posicoes), writeln(Posicoes); writeln(false).
% output: [(1,3),(1,4),(1,5)]
:- posicoes_entre((2,6), (8,6), Posicoes), writeln(Posicoes); writeln(false). 
% output: [(3,6),(4,6),(5,6),(6,6),(7,6)]

%--------------------------------
% cria_pontes (P6)
%--------------------------------

% Teste 15 - Publico
% Predicado cria_pontes (P6)
% Cotacao total: 0.5
% Cotacao parcial: 0

:- cria_ponte((1,2), (1,6), Ponte), writeln(Ponte); writeln(false).  
% output: ponte((1,2),(1,6))
:- cria_ponte((2,6), (8,6), Ponte), writeln(Ponte); writeln(false).  
% output: ponte((2,6),(8,6))

%--------------------------------
% caminho_livre (P7)
%--------------------------------

% Teste 17 - Publico
% Predicado caminho_livre (P7)
% Cotacao total: 1 (0.5)
% Cotacao parcial: 0
:- caminho_livre((4,1), (4,5), [(4, 2), (4, 3), (4, 4)], ilha(_, (1,3)), ilha(_, (3,3))), writeln(true); writeln(false).
% output: true
:- caminho_livre((4,1), (4,5), [(4, 2), (4, 3), (4, 4)], ilha(_, (1,3)), ilha(_, (5,3))), writeln(false); writeln(true).
% output: true

% Teste 19 - Publico
% Predicado caminho_livre (P7)
% Cotacao total: 1 (0.5)
% Cotacao parcial: 0

:- caminho_livre((4,1), (4,5), [(4, 2), (4, 3), (4, 4)], ilha(_, (1,3)), ilha(_, (5,3))), writeln(false); writeln(true).
% output: true
:- caminho_livre((4,1), (4,5), [(4, 2), (4, 3), (4, 4)], ilha(_, (5,3)), ilha(_, (7,3))), writeln(true); writeln(false72).
% output: true

%--------------------------------
% actualiza_vizinhas_entrada (P8)
%--------------------------------

% Teste 21 - Publico
% Predicado actualiza_vizinhas_entrada (P8)
% Cotacao total: 1 (0.5)
% Cotacao parcial: 0

:- actualiza_vizinhas_entrada((4,1), (4,5), [(4, 2), (4, 3), (4, 4)], [ilha(2, (1,3)), [ilha(2, (3,3))], []], NovaEntrada), writeln(NovaEntrada); writeln(false).
% output: [ilha(2,(1,3)),[ilha(2,(3,3))],[]]
:- actualiza_vizinhas_entrada((4,1), (4,5), [(4, 2), (4, 3), (4, 4)], [ilha(2, (1,3)), [ilha(2, (5,3))], []], NovaEntrada), writeln(NovaEntrada); writeln(false). 
% output: [ilha(2,(1,3)),[],[]]

% Teste 23 - Publico
% Predicado actualiza_vizinhas_entrada (P8)
% Cotacao total: 1 (0.5)
% Cotacao parcial: 0

:- actualiza_vizinhas_entrada((1, 4), (5, 4), [(2, 4), (3, 4), (4, 4)], [ilha(2, (3,1)), [ilha(2, (3,5))], []], NovaEntrada), writeln(NovaEntrada); writeln(false). 
% output: [ilha(2,(3,1)),[],[]]
:- actualiza_vizinhas_entrada((1, 4), (5, 4), [(2, 4), (3, 4), (4, 4)], [ilha(2, (3,5)), [ilha(2, (3,7))], []], NovaEntrada), writeln(NovaEntrada); writeln(false).
% output: [ilha(2,(3,5)),[ilha(2,(3,7))],[]]

%--------------------------------
% actualiza_vizinhas_apos_pontes (P9)
%--------------------------------

% Teste 25 - Publico
% Predicado actualiza_vizinhas_apos_pontes (P9)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(1,(1,1)),[ilha(4,(1,3))],[]],[ilha(4,(1,3)),[ilha(1,(1,1)),ilha(1,(1,5)),ilha(2,(3,3))],[]],[ilha(1,(1,5)),[ilha(4,(1,3))],[]],[ilha(2,(3,3)),[ilha(4,(1,3))],[]]], actualiza_vizinhas_apos_pontes(Estado, (1, 3), (3, 3), NovoEstado), writeln(NovoEstado); writeln(false). 
% output: [[ilha(1,(1,1)),[ilha(4,(1,3))],[]],[ilha(4,(1,3)),[ilha(1,(1,1)),ilha(1,(1,5)),ilha(2,(3,3))],[]],[ilha(1,(1,5)),[ilha(4,(1,3))],[]],[ilha(2,(3,3)),[ilha(4,(1,3))],[]]]
:- Estado = [[ilha(4,(1,2)),[ilha(3,(1,10)),ilha(2,(3,2))],[]],[ilha(3,(1,10)),[ilha(4,(1,2)),ilha(4,(4,10))],[]],[ilha(3,(2,3)),[ilha(3,(2,7)),ilha(4,(4,3))],[]],[ilha(3,(2,7)),[ilha(3,(2,3)),ilha(1,(2,9)),ilha(1,(5,7))],[]],[ilha(1,(2,9)),[ilha(3,(2,7))],[]],[ilha(2,(3,2)),[ilha(4,(1,2))],[]],[ilha(3,(4,1)),[ilha(4,(4,3)),ilha(3,(7,1))],[]],[ilha(4,(4,3)),[ilha(3,(2,3)),ilha(3,(4,1)),ilha(4,(4,10)),ilha(4,(10,3))],[]],[ilha(4,(4,10)),[ilha(3,(1,10)),ilha(4,(4,3)),ilha(4,(8,10))],[]],[ilha(1,(5,7)),[ilha(3,(2,7)),ilha(5,(8,7))],[]],[ilha(1,(6,4)),[ilha(3,(8,4))],[]],[ilha(3,(7,1)),[ilha(3,(4,1)),ilha(2,(10,1))],[]],[ilha(3,(8,4)),[ilha(1,(6,4)),ilha(5,(8,7))],[]],[ilha(5,(8,7)),[ilha(1,(5,7)),ilha(3,(8,4)),ilha(4,(8,10)),ilha(1,(10,7))],[]],[ilha(4,(8,10)),[ilha(4,(4,10)),ilha(5,(8,7)),ilha(1,(10,10))],[]],[ilha(2,(10,1)),[ilha(3,(7,1)),ilha(4,(10,3))],[]],[ilha(4,(10,3)),[ilha(4,(4,3)),ilha(2,(10,1)),ilha(2,(10,5))],[]],[ilha(2,(10,5)),[ilha(4,(10,3)),ilha(1,(10,7))],[]],[ilha(1,(10,7)),[ilha(5,(8,7)),ilha(2,(10,5)),ilha(1,(10,10))],[]],[ilha(1,(10,10)),[ilha(4,(8,10)),ilha(1,(10,7))],[]]], actualiza_vizinhas_apos_pontes(Estado, (1, 2), (3, 2), NovoEstado), writeln(NovoEstado); writeln(false). 
% output: [[ilha(4,(1,2)),[ilha(3,(1,10)),ilha(2,(3,2))],[]],[ilha(3,(1,10)),[ilha(4,(1,2)),ilha(4,(4,10))],[]],[ilha(3,(2,3)),[ilha(3,(2,7)),ilha(4,(4,3))],[]],[ilha(3,(2,7)),[ilha(3,(2,3)),ilha(1,(2,9)),ilha(1,(5,7))],[]],[ilha(1,(2,9)),[ilha(3,(2,7))],[]],[ilha(2,(3,2)),[ilha(4,(1,2))],[]],[ilha(3,(4,1)),[ilha(4,(4,3)),ilha(3,(7,1))],[]],[ilha(4,(4,3)),[ilha(3,(2,3)),ilha(3,(4,1)),ilha(4,(4,10)),ilha(4,(10,3))],[]],[ilha(4,(4,10)),[ilha(3,(1,10)),ilha(4,(4,3)),ilha(4,(8,10))],[]],[ilha(1,(5,7)),[ilha(3,(2,7)),ilha(5,(8,7))],[]],[ilha(1,(6,4)),[ilha(3,(8,4))],[]],[ilha(3,(7,1)),[ilha(3,(4,1)),ilha(2,(10,1))],[]],[ilha(3,(8,4)),[ilha(1,(6,4)),ilha(5,(8,7))],[]],[ilha(5,(8,7)),[ilha(1,(5,7)),ilha(3,(8,4)),ilha(4,(8,10)),ilha(1,(10,7))],[]],[ilha(4,(8,10)),[ilha(4,(4,10)),ilha(5,(8,7)),ilha(1,(10,10))],[]],[ilha(2,(10,1)),[ilha(3,(7,1)),ilha(4,(10,3))],[]],[ilha(4,(10,3)),[ilha(4,(4,3)),ilha(2,(10,1)),ilha(2,(10,5))],[]],[ilha(2,(10,5)),[ilha(4,(10,3)),ilha(1,(10,7))],[]],[ilha(1,(10,7)),[ilha(5,(8,7)),ilha(2,(10,5)),ilha(1,(10,10))],[]],[ilha(1,(10,10)),[ilha(4,(8,10)),ilha(1,(10,7))],[]]]

%--------------------------------
% ilhas_terminadas (P10)
%--------------------------------

% Teste 27 - Publico
% Predicado ilhas_terminadas (P10)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(2,(1,3)),[ilha(6,(3,3))],[_, _]],[ilha(1,(3,1)),[ilha(6,(3,3))],[_]],[ilha(6,(3,3)),[ilha(2,(1,3)),ilha(1,(3,1)),ilha(1,(3,5)),ilha(2,(5,3))],[_, _, _, _, _, _]]], ilhas_terminadas(Estado, IlhasTerminadas), IlhasTerminadas == [ilha(2,(1,3)),ilha(1,(3,1)),ilha(6,(3,3))], writeln(true); writeln(false).
% output: true

% Teste 29 - Publico
% Predicado ilhas_terminadas (P10)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(2,(1,3)),[ilha(6,(3,3))],[_, _]],[ilha(1,(3,1)),[ilha(6,(3,3))],[]],[ilha(6,(3,3)),[ilha(2,(1,3)),ilha(1,(3,1)),ilha(1,(3,5)),ilha(2,(5,3))],[_, _, _, _, _, _]]], ilhas_terminadas(Estado, IlhasTerminadas), IlhasTerminadas = [ilha(2,(1,3)),ilha(6,(3,3))], writeln(true); writeln(false).
% output: true

:- Estado = [[ilha(2,(1,3)),[ilha(6,(3,3))],[_, _]],[ilha(1,(3,1)),[ilha(6,(3,3))],[]],[ilha('X',(3,3)),[ilha(2,(1,3)),ilha(1,(3,1)),ilha(1,(3,5)),ilha(2,(5,3))],[_, _, _, _, _, _]]], ilhas_terminadas(Estado, IlhasTerminadas), IlhasTerminadas = [ilha(2,(1,3))], writeln(true); writeln(false).
% output: true

%--------------------------------
% tira_ilhas_terminadas_entrada (P11)
%--------------------------------

% Teste 31 - Publico
% Predicado tira_ilhas_terminadas_entrada (P11)
% Cotacao total: 1
% Cotacao parcial: 0

:- Entrada = [ilha(4,(6,5)),[ilha(1,(3,5)),ilha(3,(6,3)),ilha(5,(3,1))],[]], Ilhas_term = [ilha(1,(3,5)), ilha(5,(3,1))],tira_ilhas_terminadas_entrada(Ilhas_term, Entrada, NovaEntrada), writeln(NovaEntrada); writeln(false).  
% output: [ilha(4,(6,5)),[ilha(3,(6,3))],[]]
:- Entrada = [ilha(4,(6,5)),[ilha(1,(3,5))],[]], Ilhas_term = [ilha(1,(3,5)), ilha(5,(3,1))],tira_ilhas_terminadas_entrada(Ilhas_term, Entrada, NovaEntrada), writeln(NovaEntrada); writeln(false).  
% output: [ilha(4,(6,5)),[],[]]

%--------------------------------
% tira_ilhas_terminadas (P12)
%--------------------------------

% Teste 33 - Publico
% Predicado tira_ilhas_terminadas (P12)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(2,(1,1)),[ilha(4,(4,1))],[]],[ilha(2,(2,3)),[ilha(4,(2,5)),ilha(3,(4,3))],[]],[ilha(4,(2,5)),[ilha(2,(2,3)),ilha(2,(2,7)),ilha(4,(7,5))],[]],[ilha(2,(2,7)),[ilha(4,(2,5)),ilha(1,(5,7))],[]],[ilha(4,(4,1)),[ilha(2,(1,1)),ilha(3,(4,3))],[]],[ilha(3,(4,3)),[ilha(2,(2,3)),ilha(4,(4,1))],[]],[ilha(3,(5,2)),[ilha(2,(5,4)),ilha(2,(7,2))],[]],[ilha(2,(5,4)),[ilha(3,(5,2)),ilha(1,(5,7))],[]],[ilha(1,(5,7)),[ilha(2,(2,7)),ilha(2,(5,4)),ilha(1,(7,7))],[]],[ilha(2,(7,2)),[ilha(3,(5,2)),ilha(4,(7,5))],[]],[ilha(4,(7,5)),[ilha(4,(2,5)),ilha(2,(7,2)),ilha(1,(7,7))],[]],[ilha(1,(7,7)),[ilha(1,(5,7)),ilha(4,(7,5))],[]]], IlhasTerm = [], tira_ilhas_terminadas(Estado, IlhasTerm, NovoEstado), writeln(NovoEstado); writeln(false).
% output: [[ilha(2,(1,1)),[ilha(4,(4,1))],[]],[ilha(2,(2,3)),[ilha(4,(2,5)),ilha(3,(4,3))],[]],[ilha(4,(2,5)),[ilha(2,(2,3)),ilha(2,(2,7)),ilha(4,(7,5))],[]],[ilha(2,(2,7)),[ilha(4,(2,5)),ilha(1,(5,7))],[]],[ilha(4,(4,1)),[ilha(2,(1,1)),ilha(3,(4,3))],[]],[ilha(3,(4,3)),[ilha(2,(2,3)),ilha(4,(4,1))],[]],[ilha(3,(5,2)),[ilha(2,(5,4)),ilha(2,(7,2))],[]],[ilha(2,(5,4)),[ilha(3,(5,2)),ilha(1,(5,7))],[]],[ilha(1,(5,7)),[ilha(2,(2,7)),ilha(2,(5,4)),ilha(1,(7,7))],[]],[ilha(2,(7,2)),[ilha(3,(5,2)),ilha(4,(7,5))],[]],[ilha(4,(7,5)),[ilha(4,(2,5)),ilha(2,(7,2)),ilha(1,(7,7))],[]],[ilha(1,(7,7)),[ilha(1,(5,7)),ilha(4,(7,5))],[]]]
:- Estado = [[ilha(2,(1,1)),[ilha(4,(4,1))],[]],[ilha(2,(2,3)),[ilha(4,(2,5)),ilha(3,(4,3))],[]],[ilha(4,(2,5)),[ilha(2,(2,3)),ilha(2,(2,7)),ilha(4,(7,5))],[]],[ilha(2,(2,7)),[ilha(4,(2,5)),ilha(1,(5,7))],[]],[ilha(4,(4,1)),[ilha(2,(1,1)),ilha(3,(4,3))],[]],[ilha(3,(4,3)),[ilha(2,(2,3)),ilha(4,(4,1))],[]],[ilha(3,(5,2)),[ilha(2,(5,4)),ilha(2,(7,2))],[]],[ilha(2,(5,4)),[ilha(3,(5,2)),ilha(1,(5,7))],[]],[ilha(1,(5,7)),[ilha(2,(2,7)),ilha(2,(5,4)),ilha(1,(7,7))],[]],[ilha(2,(7,2)),[ilha(3,(5,2)),ilha(4,(7,5))],[]],[ilha(4,(7,5)),[ilha(4,(2,5)),ilha(2,(7,2)),ilha(1,(7,7))],[]],[ilha(1,(7,7)),[ilha(1,(5,7)),ilha(4,(7,5))],[]]], IlhasTerm = [ilha(2,(1,1)), ilha(2,(2,3))], tira_ilhas_terminadas(Estado, IlhasTerm, NovoEstado), writeln(NovoEstado); writeln(false). 
% output: [[ilha(2,(1,1)),[ilha(4,(4,1))],[]],[ilha(2,(2,3)),[ilha(4,(2,5)),ilha(3,(4,3))],[]],[ilha(4,(2,5)),[ilha(2,(2,7)),ilha(4,(7,5))],[]],[ilha(2,(2,7)),[ilha(4,(2,5)),ilha(1,(5,7))],[]],[ilha(4,(4,1)),[ilha(3,(4,3))],[]],[ilha(3,(4,3)),[ilha(4,(4,1))],[]],[ilha(3,(5,2)),[ilha(2,(5,4)),ilha(2,(7,2))],[]],[ilha(2,(5,4)),[ilha(3,(5,2)),ilha(1,(5,7))],[]],[ilha(1,(5,7)),[ilha(2,(2,7)),ilha(2,(5,4)),ilha(1,(7,7))],[]],[ilha(2,(7,2)),[ilha(3,(5,2)),ilha(4,(7,5))],[]],[ilha(4,(7,5)),[ilha(4,(2,5)),ilha(2,(7,2)),ilha(1,(7,7))],[]],[ilha(1,(7,7)),[ilha(1,(5,7)),ilha(4,(7,5))],[]]]

%--------------------------------
% marca_ilhas_terminadas_entrada (P13)
%--------------------------------

% Teste 35 - Publico
% Predicado marca_ilhas_terminadas_entrada (P13)
% Cotacao total: 1
% Cotacao parcial: 0

:- Entrada = [ilha(4,(6,5)),[ilha(1,(3,5)),ilha(3,(6,3)),ilha(5,(3,1))],[]], Ilhas_term = [ilha(1,(3,5)), ilha(5,(3,1))], marca_ilhas_terminadas_entrada(Ilhas_term, Entrada, NovaEntrada), writeln(NovaEntrada); writeln(false). 
% output: [ilha(4,(6,5)),[ilha(1,(3,5)),ilha(3,(6,3)),ilha(5,(3,1))],[]]
:- Entrada = [ilha(4,(6,5)),[ilha(1,(3,5))],[]], Ilhas_term = [ilha(4,(6,5)), ilha(5,(3,1))], marca_ilhas_terminadas_entrada(Ilhas_term, Entrada, NovaEntrada), writeln(NovaEntrada); writeln(false). 
% output: [ilha(X,(6,5)),[ilha(1,(3,5))],[]]

%--------------------------------
% marca_ilhas_terminadas (P14)
%--------------------------------

% Teste 37 - Publico
% Predicado marca_ilhas_terminadas (P14)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(1,(1,1)),[ilha(4,(1,3))],[]],[ilha(4,(1,3)),[ilha(1,(1,1)),ilha(1,(1,5)),ilha(2,(3,3))],[]],[ilha(1,(1,5)),[ilha(4,(1,3))],[]],[ilha(2,(3,3)),[ilha(4,(1,3))],[]]], IlhasTerminadas = [ilha(1,(1,1)), ilha(1,(1,5))], marca_ilhas_terminadas(Estado, IlhasTerminadas, NovoEstado), writeln(NovoEstado); writeln(false). 
% output: [[ilha(X,(1,1)),[ilha(4,(1,3))],[]],[ilha(4,(1,3)),[ilha(1,(1,1)),ilha(1,(1,5)),ilha(2,(3,3))],[]],[ilha(X,(1,5)),[ilha(4,(1,3))],[]],[ilha(2,(3,3)),[ilha(4,(1,3))],[]]]

%--------------------------------
% trata_ilhas_terminadas (P15)
%--------------------------------

% Teste 39 - Publico
% Predicado trata_ilhas_terminadas (P15)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(1,(1,1)),[ilha(4,(1,3))],[_]],[ilha(4,(1,3)),[ilha(1,(1,1)),ilha(1,(1,5)),ilha(2,(3,3))],[]],[ilha(1,(1,5)),[ilha(4,(1,3))],[]],[ilha(2,(3,3)),[ilha(4,(1,3))],[]]], trata_ilhas_terminadas(Estado, NovoEstado), NovoEstado = [[ilha(X,(1,1)),[ilha(4,(1,3))],[_7740]],[ilha(4,(1,3)),[ilha(1,(1,5)),ilha(2,(3,3))],[]],[ilha(1,(1,5)),[ilha(4,(1,3))],[]],[ilha(2,(3,3)),[ilha(4,(1,3))],[]]], writeln(true); writeln(false). 
% output: true

%--------------------------------
% junta_pontes (P16)
%--------------------------------

% Teste 41 - Publico
% Predicado junta_pontes (P16)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(2,(1,1)),[ilha(2,(1,3)),ilha(6,(3,1))],[]],[ilha(2,(1,3)),[ilha(2,(1,1)),ilha(5,(1,5)),ilha(3,(3,3))],[]],[ilha(5,(1,5)),[ilha(2,(1,3)),ilha(2,(1,7)),ilha(6,(4,5))],[]],[ilha(2,(1,7)),[ilha(5,(1,5)),ilha(1,(4,7))],[]],[ilha(1,(2,6)),[ilha(3,(2,8)),ilha(2,(5,6))],[]],[ilha(3,(2,8)),[ilha(1,(2,6)),ilha(6,(5,8))],[]],[ilha(6,(3,1)),[ilha(2,(1,1)),ilha(3,(3,3)),ilha(3,(5,1))],[]],[ilha(3,(3,3)),[ilha(2,(1,3)),ilha(6,(3,1)),ilha(1,(5,3))],[]],[ilha(2,(4,2)),[ilha(6,(4,5)),ilha(2,(6,2))],[]],[ilha(6,(4,5)),[ilha(5,(1,5)),ilha(2,(4,2)),ilha(1,(4,7)),ilha(5,(7,5))],[]],[ilha(1,(4,7)),[ilha(2,(1,7)),ilha(6,(4,5)),ilha(2,(8,7))],[]],[ilha(3,(5,1)),[ilha(6,(3,1)),ilha(1,(5,3)),ilha(1,(7,1))],[]],[ilha(1,(5,3)),[ilha(3,(3,3)),ilha(3,(5,1)),ilha(2,(5,6)),ilha(3,(7,3))],[]],[ilha(2,(5,6)),[ilha(1,(2,6)),ilha(1,(5,3)),ilha(6,(5,8))],[]],[ilha(6,(5,8)),[ilha(3,(2,8)),ilha(2,(5,6)),ilha(3,(7,8))],[]],[ilha(2,(6,2)),[ilha(2,(4,2)),ilha(2,(8,2))],[]],[ilha(1,(7,1)),[ilha(3,(5,1)),ilha(3,(7,3))],[]],[ilha(3,(7,3)),[ilha(1,(5,3)),ilha(1,(7,1)),ilha(5,(7,5))],[]],[ilha(5,(7,5)),[ilha(6,(4,5)),ilha(3,(7,3)),ilha(3,(7,8))],[]],[ilha(3,(7,8)),[ilha(6,(5,8)),ilha(5,(7,5))],[]],[ilha(2,(8,2)),[ilha(2,(6,2)),ilha(3,(8,4))],[]],[ilha(3,(8,4)),[ilha(2,(8,2)),ilha(2,(8,7))],[]],[ilha(2,(8,7)),[ilha(1,(4,7)),ilha(3,(8,4))],[]]], junta_pontes(Estado, 1, ilha(2,(4,2)), ilha(2,(6,2)), NovoEstado), writeln(NovoEstado); writeln(false). 
% output: [[ilha(2,(1,1)),[ilha(2,(1,3)),ilha(6,(3,1))],[]],[ilha(2,(1,3)),[ilha(2,(1,1)),ilha(5,(1,5)),ilha(3,(3,3))],[]],[ilha(5,(1,5)),[ilha(2,(1,3)),ilha(2,(1,7)),ilha(6,(4,5))],[]],[ilha(2,(1,7)),[ilha(5,(1,5)),ilha(1,(4,7))],[]],[ilha(1,(2,6)),[ilha(3,(2,8)),ilha(2,(5,6))],[]],[ilha(3,(2,8)),[ilha(1,(2,6)),ilha(6,(5,8))],[]],[ilha(6,(3,1)),[ilha(2,(1,1)),ilha(3,(3,3)),ilha(3,(5,1))],[]],[ilha(3,(3,3)),[ilha(2,(1,3)),ilha(6,(3,1)),ilha(1,(5,3))],[]],[ilha(2,(4,2)),[ilha(6,(4,5)),ilha(2,(6,2))],[ponte((4,2),(6,2))]],[ilha(6,(4,5)),[ilha(5,(1,5)),ilha(2,(4,2)),ilha(1,(4,7)),ilha(5,(7,5))],[]],[ilha(1,(4,7)),[ilha(2,(1,7)),ilha(6,(4,5)),ilha(2,(8,7))],[]],[ilha(3,(5,1)),[ilha(6,(3,1)),ilha(1,(7,1))],[]],[ilha(1,(5,3)),[ilha(3,(3,3)),ilha(2,(5,6)),ilha(3,(7,3))],[]],[ilha(2,(5,6)),[ilha(1,(2,6)),ilha(1,(5,3)),ilha(6,(5,8))],[]],[ilha(6,(5,8)),[ilha(3,(2,8)),ilha(2,(5,6)),ilha(3,(7,8))],[]],[ilha(2,(6,2)),[ilha(2,(4,2)),ilha(2,(8,2))],[ponte((4,2),(6,2))]],[ilha(1,(7,1)),[ilha(3,(5,1)),ilha(3,(7,3))],[]],[ilha(3,(7,3)),[ilha(1,(5,3)),ilha(1,(7,1)),ilha(5,(7,5))],[]],[ilha(5,(7,5)),[ilha(6,(4,5)),ilha(3,(7,3)),ilha(3,(7,8))],[]],[ilha(3,(7,8)),[ilha(6,(5,8)),ilha(5,(7,5))],[]],[ilha(2,(8,2)),[ilha(2,(6,2)),ilha(3,(8,4))],[]],[ilha(3,(8,4)),[ilha(2,(8,2)),ilha(2,(8,7))],[]],[ilha(2,(8,7)),[ilha(1,(4,7)),ilha(3,(8,4))],[]]]

% Teste 43 - Publico
% Predicado junta_pontes (P16)
% Cotacao total: 1
% Cotacao parcial: 0

:- Estado = [[ilha(2,(1,1)),[ilha(2,(1,3)),ilha(6,(3,1))],[]],[ilha(2,(1,3)),[ilha(2,(1,1)),ilha(5,(1,5)),ilha(3,(3,3))],[]],[ilha(5,(1,5)),[ilha(2,(1,3)),ilha(2,(1,7)),ilha(6,(4,5))],[]],[ilha(2,(1,7)),[ilha(5,(1,5)),ilha(1,(4,7))],[]],[ilha(1,(2,6)),[ilha(3,(2,8)),ilha(2,(5,6))],[]],[ilha(3,(2,8)),[ilha(1,(2,6)),ilha(6,(5,8))],[]],[ilha(6,(3,1)),[ilha(2,(1,1)),ilha(3,(3,3)),ilha(3,(5,1))],[]],[ilha(3,(3,3)),[ilha(2,(1,3)),ilha(6,(3,1)),ilha(1,(5,3))],[]],[ilha(2,(4,2)),[ilha(6,(4,5)),ilha(2,(6,2))],[]],[ilha(6,(4,5)),[ilha(5,(1,5)),ilha(2,(4,2)),ilha(1,(4,7)),ilha(5,(7,5))],[]],[ilha(1,(4,7)),[ilha(2,(1,7)),ilha(6,(4,5)),ilha(2,(8,7))],[]],[ilha(3,(5,1)),[ilha(6,(3,1)),ilha(1,(5,3)),ilha(1,(7,1))],[]],[ilha(1,(5,3)),[ilha(3,(3,3)),ilha(3,(5,1)),ilha(2,(5,6)),ilha(3,(7,3))],[]],[ilha(2,(5,6)),[ilha(1,(2,6)),ilha(1,(5,3)),ilha(6,(5,8))],[]],[ilha(6,(5,8)),[ilha(3,(2,8)),ilha(2,(5,6)),ilha(3,(7,8))],[]],[ilha(2,(6,2)),[ilha(2,(4,2)),ilha(2,(8,2))],[]],[ilha(1,(7,1)),[ilha(3,(5,1)),ilha(3,(7,3))],[]],[ilha(3,(7,3)),[ilha(1,(5,3)),ilha(1,(7,1)),ilha(5,(7,5))],[]],[ilha(5,(7,5)),[ilha(6,(4,5)),ilha(3,(7,3)),ilha(3,(7,8))],[]],[ilha(3,(7,8)),[ilha(6,(5,8)),ilha(5,(7,5))],[]],[ilha(2,(8,2)),[ilha(2,(6,2)),ilha(3,(8,4))],[]],[ilha(3,(8,4)),[ilha(2,(8,2)),ilha(2,(8,7))],[]],[ilha(2,(8,7)),[ilha(1,(4,7)),ilha(3,(8,4))],[]]], junta_pontes(Estado, 2, ilha(2,(4,2)), ilha(2,(6,2)), NovoEstado), writeln(NovoEstado); writeln(false). 
% output: [[ilha(2,(1,1)),[ilha(2,(1,3)),ilha(6,(3,1))],[]],[ilha(2,(1,3)),[ilha(2,(1,1)),ilha(5,(1,5)),ilha(3,(3,3))],[]],[ilha(5,(1,5)),[ilha(2,(1,3)),ilha(2,(1,7)),ilha(6,(4,5))],[]],[ilha(2,(1,7)),[ilha(5,(1,5)),ilha(1,(4,7))],[]],[ilha(1,(2,6)),[ilha(3,(2,8)),ilha(2,(5,6))],[]],[ilha(3,(2,8)),[ilha(1,(2,6)),ilha(6,(5,8))],[]],[ilha(6,(3,1)),[ilha(2,(1,1)),ilha(3,(3,3)),ilha(3,(5,1))],[]],[ilha(3,(3,3)),[ilha(2,(1,3)),ilha(6,(3,1)),ilha(1,(5,3))],[]],[ilha(X,(4,2)),[ilha(6,(4,5))],[ponte((4,2),(6,2)),ponte((4,2),(6,2))]],[ilha(6,(4,5)),[ilha(5,(1,5)),ilha(1,(4,7)),ilha(5,(7,5))],[]],[ilha(1,(4,7)),[ilha(2,(1,7)),ilha(6,(4,5)),ilha(2,(8,7))],[]],[ilha(3,(5,1)),[ilha(6,(3,1)),ilha(1,(7,1))],[]],[ilha(1,(5,3)),[ilha(3,(3,3)),ilha(2,(5,6)),ilha(3,(7,3))],[]],[ilha(2,(5,6)),[ilha(1,(2,6)),ilha(1,(5,3)),ilha(6,(5,8))],[]],[ilha(6,(5,8)),[ilha(3,(2,8)),ilha(2,(5,6)),ilha(3,(7,8))],[]],[ilha(X,(6,2)),[ilha(2,(8,2))],[ponte((4,2),(6,2)),ponte((4,2),(6,2))]],[ilha(1,(7,1)),[ilha(3,(5,1)),ilha(3,(7,3))],[]],[ilha(3,(7,3)),[ilha(1,(5,3)),ilha(1,(7,1)),ilha(5,(7,5))],[]],[ilha(5,(7,5)),[ilha(6,(4,5)),ilha(3,(7,3)),ilha(3,(7,8))],[]],[ilha(3,(7,8)),[ilha(6,(5,8)),ilha(5,(7,5))],[]],[ilha(2,(8,2)),[ilha(3,(8,4))],[]],[ilha(3,(8,4)),[ilha(2,(8,2)),ilha(2,(8,7))],[]],[ilha(2,(8,7)),[ilha(1,(4,7)),ilha(3,(8,4))],[]]]

