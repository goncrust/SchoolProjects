nao_membro(_, []).
nao_membro(E, [P | R]) :- E \== P, nao_membro(E, R).
