inserare(E, L, [E|L]).
inserare(E, [H|T], [H|Rez]):-
    inserare(E, T, Rez).

perm([], []).
perm([H|T], Rez):-
    perm(T, Rez1),
    inserare(H, Rez1, Rez).

submultimi([], []).
submultimi([_|T], Rez) :- submultimi(T, Rez).
submultimi([H|T], [H|Rez]) :- submultimi(T, Rez).

collectAll(L, R):-
    findall(Rez, submultimi(L, Rez), R).
