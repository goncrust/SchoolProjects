%           LP - Solucionador de Puzzles Hashi (Parte I)

%-------------------------------------------------------------------------------
% O objetivo deste projecto e' escrever a primeira parte de um programa
% em PROLOG para resolver puzzles hashi.
% Um puzzle hashi e' constituido por uma grelha n x m, em que cada posicao
% da grelha ou esta vazia, ou contem uma ilha, com a indicacao do numero
% de pontes que essa ilha devera ter no final da solucao.
% Para obter a solucao, as ilhas devem ser ligadas por pontes, de forma
% a:
% - nao haver mais do que duas pontes entre quaisquer duas ilhas,
% - as pontes so podem ser verticais ou horizontais e nao podem cruzar
%   ilhas ou outras pontes,
% - na solucao do puzzle, as pontes permitem a passagem entre quaisquer
%   duas ilhas.
% A representacao interna do puzzle e' uma lista de listas, correspondendo
% cada lista interior a uma linha do puzzle. Uma posicao contendo uma ilha
% e' representada por um inteiro positivo correspondente ao numero de
% pontes dessa ilha. As posicoes vazias sao representadas por zero.
%-------------------------------------------------------------------------------

:- [codigo_comum].

%-------------------------------------------------------------------------------
% extrai_ilhas_linha(N_L, Linha, Ilhas)
% Ilhas e' a lista ordenada (da esquerda para a direita) cujos elementos
% sao as ilhas da linha Linha. N_L e' o inteiro positivo correspondente ao
% numero da linha Linha.
% Uma ilha e' representada por uma estrutura: ilha(N, (Linha, Coluna))
%-------------------------------------------------------------------------------

extrai_ilhas_linha(N_L, Linha, Ilhas) :-
    extrai_ilhas_linha(N_L, Linha, Ilhas, 1).

extrai_ilhas_linha(_, [], [], _) :- !.

extrai_ilhas_linha(N_L, [N | R_Linha], [ilha(N, (N_L, N_C)) | R_Ilhas], N_C) :-
    N > 0, !,
    N_C_MaisUm is N_C + 1,
    extrai_ilhas_linha(N_L, R_Linha, R_Ilhas, N_C_MaisUm).

extrai_ilhas_linha(N_L, [_ | R_Linha], Ilhas, N_C) :-
    N_C_MaisUm is N_C + 1,
    extrai_ilhas_linha(N_L, R_Linha, Ilhas, N_C_MaisUm).

%-------------------------------------------------------------------------------
% ilhas(Puz, Ilhas)
% Ilhas e' a lista ordenada (da esquerda para a direita e de cima para baixo)
% cujos elementos sao as ilhas do puzzle Puz.
%-------------------------------------------------------------------------------

ilhas(Puz, Ilhas) :-
    ilhas(Puz, IlhasTemp, 1),
    append(IlhasTemp, Ilhas).

ilhas([], [], _) :- !.

ilhas([Linha | R1], [IlhasLinha | R2], N_L) :-
    extrai_ilhas_linha(N_L, Linha, IlhasLinha),
    N_L_MaisUm is N_L + 1,
    ilhas(R1, R2, N_L_MaisUm).
     
%-------------------------------------------------------------------------------
% vizinhas(Ilhas, Ilha, Vizinhas)
% Vizinhas e' a lista ordenada (de cima para baixo e da esquerda para a direita)
% cujos elementos sao as ilhas vizinhas de Ilha. Ilhas e' a lista de ilhas do puzzle
% que contem a Ilha.
% Duas ilhas dizem-se vizinhas sse se encontrarem na mesma linha ou mesma coluna,
% entre elas nao existir outra ilha e entre elas nao existir uma ponte que una outras
% duas ilhas quaisquer.
%-------------------------------------------------------------------------------

% Adiciona a ilha vizinha de cima 'as vizinhas atuais
vizinha_cima(Ilhas, ilha(_, (N_L, N_C)), CurrVizinhas, Vizinhas) :-
    findall(ilha(N1, (N_L1, N_C)), (member(ilha(N1, (N_L1, N_C)), Ilhas), N_L1 < N_L), IlhasCima),
    last(IlhasCima, Cima) -> append(CurrVizinhas, [Cima], Vizinhas) ; Vizinhas = CurrVizinhas.

% Adiciona a ilha vizinha da esquerda 'as vizinhas atuais
vizinha_esq(Ilhas, ilha(_, (N_L, N_C)), CurrVizinhas, Vizinhas) :-
    findall(ilha(N1, (N_L, N_C1)), (member(ilha(N1, (N_L, N_C1)), Ilhas), N_C1 < N_C), IlhasEsq),
    last(IlhasEsq, Esq) -> append(CurrVizinhas, [Esq], Vizinhas) ; Vizinhas = CurrVizinhas.

% Adiciona a ilha vizinha da direita 'as vizinhas atuais
vizinha_dir(Ilhas, ilha(_, (N_L, N_C)), CurrVizinhas, Vizinhas) :-
    findall(ilha(N1, (N_L, N_C1)), (member(ilha(N1, (N_L, N_C1)), Ilhas), N_C1 > N_C), IlhasDir),
    nth1(1, IlhasDir, Dir) -> append(CurrVizinhas, [Dir], Vizinhas) ; Vizinhas = CurrVizinhas.

% Adiciona a ilha vizinha de baixo 'as vizinhas atuais
vizinha_baixo(Ilhas, ilha(_, (N_L, N_C)), CurrVizinhas, Vizinhas) :-
    findall(ilha(N1, (N_L1, N_C)), (member(ilha(N1, (N_L1, N_C)), Ilhas), N_L1 > N_L), IlhasBaixo),
    nth1(1, IlhasBaixo, Baixo) -> append(CurrVizinhas, [Baixo], Vizinhas) ; Vizinhas = CurrVizinhas.

vizinhas(Ilhas, Ilha, Vizinhas) :-
    vizinha_cima(Ilhas, Ilha, [], Vizinhas1),
    vizinha_esq(Ilhas, Ilha, Vizinhas1, Vizinhas2),
    vizinha_dir(Ilhas, Ilha, Vizinhas2, Vizinhas3),
    vizinha_baixo(Ilhas, Ilha, Vizinhas3, Vizinhas).

%-------------------------------------------------------------------------------
% estado(Ilhas, Estado)
% Estado e' a lista ordenada cujos elementos sao as entradas referentes a
% cada uma das ilhas de Ilhas.
% Entrada e' uma lista em que o primeiro elemento e' uma ilha, o segundo
% e' a lista de vizinhas dessa ilha e o terceiro e' a lista de pontes da
% ilha (vazia inicialmente). Um estado e' uma lista de entradas.
%-------------------------------------------------------------------------------

% entrada_inicial_ilha(Ilhas, Ilha, Entrada)
entrada_inicial_ilha(Ilhas, Ilha, [Ilha, Vizinhas, []]) :-
    vizinhas(Ilhas, Ilha, Vizinhas).

estado(Ilhas, Estado) :-
    maplist(entrada_inicial_ilha(Ilhas), Ilhas, Estado).

%-------------------------------------------------------------------------------
% posicoes_entre(Pos1, Pos2, Posicoes)
% Posicoes e' a lista ordenada de posicoes entre Pos1 e Pos2 (excluindo Pos1
% e Pos2). Se Pos1 e Pos2 nao pertencem a mesma linha ou a mesma coluna,
% o resultado e false.
%-------------------------------------------------------------------------------

lista_between(N1, N2, ListaBetween) :-
    N1MaisUm is N1+1,
    N1MenosUm is N1-1,
    N2MaisUm is N2+1,
    N2MenosUm is N2-1,
    findall(N, (N1 < N2 -> between(N1MaisUm, N2MenosUm, N) ; between(N2MaisUm, N1MenosUm, N)), ListaBetween).

posicoes_entre_horizontal((Y, X1), (Y, X2), Posicoes) :-
    X1 == X2 -> Posicoes = [] ;
    lista_between(X1, X2, ListaBetween),
    findall((Y, X), (member(X, ListaBetween)), Posicoes).

posicoes_entre_vertical((Y1, X), (Y2, X), Posicoes) :-
    Y1 == Y2 -> Posicoes = [] ;
    lista_between(Y1, Y2, ListaBetween),
    findall((Y, X), (member(Y, ListaBetween)), Posicoes).

posicoes_entre((Y1, X1), (Y2, X2), Posicoes) :-
    (Y1 == Y2 -> posicoes_entre_horizontal((Y1, X1), (Y2, X2), Posicoes) ;
        (X1 == X2 -> posicoes_entre_vertical((Y1, X1), (Y2, X2), Posicoes) ; fail)).

%-------------------------------------------------------------------------------
% cria_ponte(Pos1, Pos2, Ponte)
% Ponte e' uma ponte entre Pos1 e Pos2.
%-------------------------------------------------------------------------------

posicoes_iguais(Pos, Pos).

posicoes_diagonal((Y1, X1), (Y2, X2)) :-
    Y1 \== Y2, X1 \== X2.

primeira_posicao((Y1, X1), (Y2, X2), Res) :-
    Y1 == Y2 -> (X1 < X2 -> Res = (Y1, X1) ; Res = (Y2, X2)) ;
    (Y1 < Y2 -> Res = (Y1, X1) ; Res = (Y2, X2)).
    
cria_ponte(Pos1, Pos2, Ponte) :-
    posicoes_iguais(Pos1, Pos2) -> fail ;
    (posicoes_diagonal(Pos1, Pos2) -> fail ;
    (primeira_posicao(Pos1, Pos2, Pos1) -> Ponte = ponte(Pos1, Pos2) ; Ponte = ponte(Pos2, Pos1))).

%-------------------------------------------------------------------------------
% caminho_livre(Pos1, Pos2, Posicoes, I, Vz)
% Pos1 e Pos2 sao posicoes, Posicoes e' a lista ordenada entre Pos1 e Pos2,
% I e' uma ilha, e Vz e' uma das suas vizinhas. Significa que a adicao
% da ponte ponte(Pos1, Pos2) nao faz com que I e Vz deixem de ser vizinhas.
%-------------------------------------------------------------------------------

caminho_livre(Pos1, Pos2, _, ilha(_, Pos1), ilha(_, Pos2)) :- !.
caminho_livre(Pos1, Pos2, _, ilha(_, Pos2), ilha(_, Pos1)) :- !.

caminho_livre(_, _, Posicoes, ilha(_, PosI), ilha(_, PosVz)) :-
    posicoes_entre(PosI, PosVz, PosicoesEntreIlhas),
    findall(P, (member(P, PosicoesEntreIlhas), member(P, Posicoes)), Intersecoes),
    Intersecoes == [].

%-------------------------------------------------------------------------------
% actualiza_vizinhas_entrada(Pos1, Pos2, Posicoes, Entrada, Nova_Entrada)
% Nova_Entrada e' igual a Entrada, excepto 'a lista de ilhas vizinhas,
% que deve ser atualizada, removendo as ilhas que deixaram de ser vizinhas apos
% a adicao da ponte(Pos1, Pos2). Posicoes e' a lista ordenada de posicoes
% entre Pos1 e Pos2.
%-------------------------------------------------------------------------------

actualiza_vizinhas_entrada(Pos1, Pos2, Posicoes, [Ilha, Vizinhas, P], [Ilha, VizinhasNovo, P]) :-
    include(caminho_livre(Pos1, Pos2, Posicoes, Ilha), Vizinhas, VizinhasNovo).

%-------------------------------------------------------------------------------
% actualiza_vizinhas_apos_pontes(Estado, Pos1, Pos2, Novo_estado)
% Novo_estado e' o estado que se obtem de Estado apos a actualizacao das ilhas
% vizinhas de cada uma das suas entradas, com o adicionar da ponte(Pos1, Pos2).
%-------------------------------------------------------------------------------

actualiza_vizinhas_apos_pontes(Estado, Pos1, Pos2, Novo_estado) :-
    posicoes_entre(Pos1, Pos2, PosicoesPonte),
    maplist(actualiza_vizinhas_entrada(Pos1, Pos2, PosicoesPonte), Estado, Novo_estado).

%-------------------------------------------------------------------------------
% ilhas_terminadas(Estado, Ilhas_term)
% Ilhas_term e' a lista de ilhas que ja tem todas as pontes associadas,
% designadas ilhas terminadas.
% Se a entrada referente a uma ilha for [ilha(N_pontes, Pos), Vizinhas, Pontes],
% esta ilha esta terminada se N_pontes for diferente de 'X' e o comprimento da
% lista Pontes for N_pontes.
%-------------------------------------------------------------------------------

ilhas_terminadas(Estado, Ilhas_term) :-
    findall(ilha(N_pontes, Pos), (member([ilha(N_pontes, Pos), _, Pontes], Estado),
        N_pontes \== 'X', proper_length(Pontes, N_pontes)), Ilhas_term).

%-------------------------------------------------------------------------------
% tira_ilhas_terminadas_entrada(Ilhas_term, Entrada, Nova_entrada)
% Nova_entrada e' a entrada resultante de remover as ilhas de Ilhas_term da
% lista de ilhas vizinhas da Entrada.
%-------------------------------------------------------------------------------

tira_ilhas_terminadas_entrada(Ilhas_term, [Ilha, Vizinhas, Pontes], [Ilha, VizinhasNovo, Pontes]) :-
    findall(I, (member(I, Vizinhas), \+ member(I, Ilhas_term)), VizinhasNovo).

%-------------------------------------------------------------------------------
% tira_ilhas_terminadas(Estado, Ilhas_term, Novo_estado)
% Novo_estado e' o resultante de aplicar o predicado tira_ilhas_terminadas_entrada
% a cada uma das entradas de Estado, sabendo que Ilhas_term sao as ilhas terminadas.
%-------------------------------------------------------------------------------

tira_ilhas_terminadas(Estado, Ilhas_term, Novo_estado) :-
    maplist(tira_ilhas_terminadas_entrada(Ilhas_term), Estado, Novo_estado).

%-------------------------------------------------------------------------------
% marca_ilhas_terminadas_entrada(Ilhas_term, Entrada, Nova_entrada)
% Nova_entrada e' a entrada obtida de Entrada da seguinte forma:
% - se a ilha de Entrada pertencer a Ilhas_term, o numero de pontes desta
% e' substituido por 'X'
% - em caso contrario, Nova_entrada e' igual a Entrada
%-------------------------------------------------------------------------------

marca_ilhas_terminadas_entrada(Ilhas_term, [ilha(N, PosI), V, P], [ilha('X', PosI), V, P]) :-
    member(ilha(N, PosI), Ilhas_term), !.

marca_ilhas_terminadas_entrada(_, E, E).

%-------------------------------------------------------------------------------
% marca_ilhas_terminadas(Estado, Ilhas_term, Novo_estado)
% Novo_estado e' o resultante de aplicar o predicado marca_ilhas_terminadas_entrada
% a cada uma das entradas de Estado, sabendo que Ilhas_term sao as ilhas terminadas.
%-------------------------------------------------------------------------------

marca_ilhas_terminadas(Estado, Ilhas_term, Novo_estado) :-
    maplist(marca_ilhas_terminadas_entrada(Ilhas_term), Estado, Novo_estado).

%-------------------------------------------------------------------------------
% trata_ilhas_terminadas(Estado, Novo_estado)
% Novo_estado e' o estado resultante de aplicar os predicados
% tira_ilhas_terminadas e marca_ilhas_terminadas a Estado.
%-------------------------------------------------------------------------------

trata_ilhas_terminadas(Estado, Novo_estado) :-
    ilhas_terminadas(Estado, Ilhas_term),
    tira_ilhas_terminadas(Estado, Ilhas_term, Novo_estado_temp),
    marca_ilhas_terminadas(Novo_estado_temp, Ilhas_term, Novo_estado).

%-------------------------------------------------------------------------------
% junta_pontes(Estado, Num_pontes, Ilha1, Ilha2, Novo_estado)
% Novo_estado e' o estado que se obtem de Estado por adicao de Num_pontes pontes
% entre Ilha1 e Ilha2.
%-------------------------------------------------------------------------------

cria_lista_novas_pontes(Ponte, Novas_pontes, Num_pontes) :-
    length(Novas_pontes, Num_pontes),
    maplist(=(Ponte), Novas_pontes).

append_novas_pontes(Novas_pontes, Ilha1, _, [Ilha1, V, P], [Ilha1, V, NovoP]) :-
    append(P, Novas_pontes, NovoP), !.

append_novas_pontes(Novas_pontes, _, Ilha2, [Ilha2, V, P], [Ilha2, V, NovoP]) :-
    append(P, Novas_pontes, NovoP), !.

append_novas_pontes(_, _, _, E, E).

junta_pontes(Estado, Num_pontes, ilha(N1, P1), ilha(N2, P2), Novo_estado) :-
    cria_ponte(P1, P2, Ponte),
    cria_lista_novas_pontes(Ponte, Novas_pontes, Num_pontes),
    maplist(append_novas_pontes(Novas_pontes, ilha(N1, P1), ilha(N2, P2)), Estado, Novo_estado_temp),
    actualiza_vizinhas_apos_pontes(Novo_estado_temp, P1, P2, Novo_estado_temp_2),
    trata_ilhas_terminadas(Novo_estado_temp_2, Novo_estado).

%-------------------------------------------------------------------------------
