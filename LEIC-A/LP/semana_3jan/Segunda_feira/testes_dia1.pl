/*

Testar predicado do dia 1

*/

:- MensagemDia1 = [a, 4, m, 77, a, 2, 1, 4, t, r, 4, 6, i, 1, 2, z, s, 4, 66, 99, e, 8, 17, r, 88, a, 17, c, h, a, 889, 1, 2, v, e],
    eliminaNumeros(MensagemDia1, MensagemDecifrada),
    writeln('Predicado eliminaNumeros (dia 1):'),
    writeln(MensagemDecifrada),
    nl,
    writeln('Resultado correto (dia 1):'),
    writeln([a,m,a,t,r,i,z,s,e,r,a,c,h,a,v,e]).


