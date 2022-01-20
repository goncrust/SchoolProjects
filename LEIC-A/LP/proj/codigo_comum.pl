%-------------------------------------------------------------------------------
% Os predicados
% escreve_Puzzle(Puzzle, Estado) e escreve_diferencas(Estado1, Estado2)
% podem ser uteis durante o desenvolvimento do projeto.
%-------------------------------------------------------------------------------


%-------------------------------------------------------------------------------
%                escreve_Puzzle(Puzzle, Estado)
%-------------------------------------------------------------------------------
escreve_Puzzle(Puz, Estado) :-
    findall(Ps, (member([_,_,Ps], Estado), Ps \= []), Lst_lst_pontes),
    tira_repetidas(Lst_lst_pontes, Lst_pontes),
    divide_pontes(Lst_pontes, Pontes_horizontais, Pontes_verticais),
    maplist(posicoes_ponte, Pontes_horizontais, Posicoes_horizontais_temp),
    maplist(posicoes_ponte, Pontes_verticais, Posicoes_verticais_temp),
    append(Posicoes_horizontais_temp, Posicoes_horizontais),
    append(Posicoes_verticais_temp, Posicoes_verticais),
    processa_horizontais(Puz, Posicoes_horizontais, Temp),
    processa_verticais(Temp, Posicoes_verticais, N_Puz),
    escreve_final(N_Puz).

escreve_car(Car) :-
    integer(Car), Car > 0, !, write(' '), write(Car), write(' ')
                                ;
     Car == 0, !, write('   ')
                           ;
     Car == '||', !, write(' '), write('"'), write(' ')
                      ;
    write(' '), write(Car), write(' ').

escreve_linha(L) :-
    maplist(escreve_car, L), nl.

escreve_final(Puz) :-
    maplist(escreve_linha, Puz).
    
tira_repetidas(Lst_lst_pontes, Lst_pontes) :-
    tira_repetidas(Lst_lst_pontes, Lst_pontes, []).

tira_repetidas([], Exist, Exist) :- !.
tira_repetidas([P | R], Lst_pontes, Exist) :-
    maplist(subtract_inv(P), R, N_R),
    append(Exist, P, N_Exist),
    tira_repetidas(N_R, Lst_pontes, N_Exist).

subtract_inv(L1, L2, Res) :- subtract(L2, L1, Res).

ponte_horizontal(ponte((L,_),(L,_))).
ponte_vertical(ponte((_,C),(_,C))).

divide_pontes(Pontes, Pontes_horizontais, Pontes_verticais) :-
    include(ponte_horizontal, Pontes, Pontes_horizontais),
    subtract(Pontes, Pontes_horizontais, Pontes_verticais).

posicoes_ponte(ponte(P1, P2), Ps) :- posicoes_entre(P1, P2, Ps).

processa_horizontais(Puz, Posicoes_horizontais, N_Puz) :-
    transforma(Puz, processa_horizontal, [], Posicoes_horizontais, N_Puz).

processa_verticais(Puz, Posicoes_verticais, N_Puz) :-
    transforma(Puz, processa_vertical, [], Posicoes_verticais, N_Puz).

processa_horizontal(Puz, Pos, N_Puz) :-
    mat_ref(Puz, Pos, Cont),
    (Cont = 0, !, mat_muda_posicao(Puz, Pos, '-', N_Puz)
                                   ;
     mat_muda_posicao(Puz, Pos, '=', N_Puz)).

processa_vertical(Puz, Pos, N_Puz) :-
    mat_ref(Puz, Pos, Cont),
    (Cont = 0, !, mat_muda_posicao(Puz, Pos, '|', N_Puz)
                               ;
     mat_muda_posicao(Puz, Pos, '||', N_Puz)).

%-----------------------------------------------------------------------------
% mat_ref(Mat, Pos, Cont):
%-----------------------------------------------------------------------------
mat_ref(Mat, (L, C), Cont) :-
    nth1(L, Mat, Linha),
    nth1(C, Linha, Cont).
%-----------------------------------------------------------------------------
% mat_muda_posicao(Mat, Pos, Cont, N_Mat):
% N_Mat e' o resultado de substituir o conteudo da posicao Pos
% de Mat por Cont.
%-----------------------------------------------------------------------------

mat_muda_posicao(Mat, (L,C), Cont, N_Mat) :-
    nth1(L,Mat,Linha),
    mat_muda_linha(Linha,C,Cont, N_Linha),
    mat_muda_linha(Mat,L,N_Linha, N_Mat),!.

%-----------------------------------------------------------------------------
% mat_muda_posicoes(Mat, Lst_Posicoes, Lst_Cont, N_Mat):
% N_Mat e' o resultado de substituir o conteudo das posicoes de
% Lst_Posicoes de Mat pelo elemento correpondente de Lst_Cont.
%-----------------------------------------------------------------------------
mat_muda_posicoes(Mat, [], _, Mat) :- !.

mat_muda_posicoes(Mat, [Pos | R_Pos], [Cont | R_Cont], N_Mat) :-
    mat_muda_posicao(Mat, Pos, Cont, Temp),
    mat_muda_posicoes(Temp, R_Pos, R_Cont, N_Mat).

%-----------------------------------------------------------------------------
% mat_muda_linha(Mat, L, N_Linha_L, N_Mat):
% N_Mat e' o resultado de substituir a linha L de Mat
% por N_Linha_L.
%-----------------------------------------------------------------------------
mat_muda_linha([_|T], 1, N_Linha_L, [N_Linha_L|T]) :- !.

mat_muda_linha([H|T], L, N_Linha_L, [H|R]):-
    L > 0,
    NL is L-1,
    mat_muda_linha(T, NL, N_Linha_L, R), !.

%-----------------------------------------------------------------------------
%transforma
%-----------------------------------------------------------------------------
%transforma(Est, Pred, Args, Lst, N_Est).
%Pred(Est, Args, El_Lst, N_est)

transforma(Est, _, _, [], Est).

transforma(Est, Pred, Args, [P | R], N_Est) :-
    append([[Est], Args, [P, Temp]], Todos_args),
    Lit =.. [Pred | Todos_args],
    call(Lit),
    transforma(Temp, Pred, Args, R, N_Est).

%-----------------------------------------------------------------------------
% Comparacao de estados
%-----------------------------------------------------------------------------
compara_estados(Est1, Est2, Difs) :-
    length(Est1, N_Ents),
    findall([Ent1, Ent2],
            (between(1, N_Ents, I), nth1(I, Est1, Ent1), nth1(I, Est2, Ent2), Ent1 \= Ent2),
            Difs).

escreve_diferencas(Est1, Est2) :-
    compara_estados(Est1, Est2, Difs),
    maplist(escreve_dif, Difs).

escreve_dif([Ent1, Ent2]) :-
    writeln(Ent1),
    writeln('passou a'),
    writeln(Ent2),
    nl.
