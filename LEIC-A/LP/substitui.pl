% substitui(L1, El, Novo, L2)
substitui([], _, _, []).
substitui([El | R1], El, Novo, [Novo | R2]) :- substitui(R1, El, Novo, R2).
substitui([P | R1], El, Novo, [P | R2]) :- substitui(R1, El, Novo, R2).
