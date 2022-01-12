duplica_elementos([], []).
duplica_elementos([P | R], [P, P | R2]) :- duplica_elementos(R, R2).
