candidat([E|_], E).
candidat([_|T], E) :- candidat(T, E).

solutie(L, N, Rez) :- candidat(L, E), E=<N, solutie_aux(L, N, Rez, [E], E).

solutie_aux(_, N, Rez, Rez, N):-!.
solutie_aux(L, N, Rez, [H|Col], S):-
    candidat(L, E),
    E<H,
    S1 is S + E,
    S1 =< N,
    solutie_aux(L, N, Rez, [E|[H|Col]], S1).
