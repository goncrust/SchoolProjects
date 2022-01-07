/*

Testar predicado do dia 4

*/

:- MatrizComMensagensDecifradasDia1 = [
[a, m, a, t, r, i, z, s, e, r, a, c, h, a, v, e],
[s, e, t, e, p, o, r, d, e, z, a, s, s, e, i, s],
[f, r, a, s, e, s, s, e, m, s, e, n, t, i, d, o],
[p, e, r, u, c, a, n, e, g, r, a, e, b, o, n, e],
[o, c, u, l, o, s, e, b, o, t, a, s, n, e, v, e],
[f, r, a, s, e, s, c, o, m, v, e, r, d, a, d, e],
[t, u, d, o, a, p, o, s, t, o, s, r, o, u, b, o]], 
MensagensDecifradasDia2 = [[7, 15], [1, 1], [5, 13], [1, 12], [5, 5], 
[2, 8], [2, 2], [7, 6], [5, 1], [4, 3], [2, 3], [4, 4], 
[4, 9], [1, 3], [5, 4], [4, 5], [1, 6], [4, 7], [5, 2], 
[4, 14], [7, 3], [7, 16], [5, 3],[6, 9]], 
programaDia4(MatrizComMensagensDecifradasDia1, MensagensDecifradasDia2, MensagemDescodificada),
    nl, nl, writeln('Predicado programaDia4 (dia 4):'),
    writeln(MensagemDescodificada),
    nl,
    writeln('Resultado correto (dia 4):'),
    writeln([b,a,n,c,o,d,e,p,o,r,t,u,g,a,l,c,i,n,c,o,d,o,u,m]).
