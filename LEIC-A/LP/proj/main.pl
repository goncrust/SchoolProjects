% LP - Solucionador de Puzzles Hashi (Projecto)
%



:- [codigo_comum].

%-------------------------------------------------------------------------------
% extrai_ilhas_linha(N_L, Linha, Ilhas)
% Ilhas e' a lista ordenada (da esquerda para a direita) cujos elementos
% sao as ilhas da linha Linha. N_L e' o inteiro positivo correspondente ao
% numero da linha Linha.
% Uma ilha e' representada por uma estrutura: ilha(N, (Linha, Coluna))
%-------------------------------------------------------------------------------

%extrai_ilhas_linha(N_L, Linha, Ilhas) :-
%    findall(ilha(N, (N_L, N_C)), (member(N, Linha), nth1(N_C, Linha, N), N > 0), Ilhas).

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

%ilhas(Puz, Ilhas) :-
%    findall(IlhasLinha, (member(Linha, Puz), nth1(N_L, Puz, Linha), extrai_ilhas_linha(N_L, Linha, IlhasLinha)), IlhasTemp),
%    append(IlhasTemp, Ilhas).

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
