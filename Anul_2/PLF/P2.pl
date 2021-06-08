% Sa se interclaseze fara pastrarea dublurilor doua liste sortate.
% Interclaseaza doua liste ordonate
% interclasare(L1, L2, R)
% L1 - lista ordonata, L2 - lista ordonata, R - lista interclasata
% (i,i,i), (i,i,o)
interclasare([], [], []):-!.
interclasare(X, [], X):-!.
interclasare([], Y, Y):-!.

%try

interclasare([H1,H2|T], L, R):-
    H1 == H2,!,
    interclasare([H2|T], L, R).

interclasare(L, [H1, H2|T], R):-
    H1 == H2,!,
    interclasare(L, [H2|T], R).

%try

interclasare([H1|T1], [H2|T2], [H1|R]):-
    H1 == H2,
    interclasare(T1, T2, R).

interclasare([H1|T1], [H2|T2], [H1|R]):-
    H1<H2,
    interclasare(T1, [H2|T2], R).

interclasare([H1|T1], [H2|T2], [H2|R]):-
    H1>H2,
    interclasare([H1|T1], T2, R).

% Interclaseaza sublistele dintr-o lista eterogena fara pastrarea
% dublurilor
% intercl_et(L, R)
% L - lista eterogena
% R - lista rezultat
% (i, i), (i, o)
intercl_et([], []).

intercl_et([H|T], R):-
    is_list(H), !,
    intercl_et(T, R1),
    interclasare(H, R1, R).

intercl_et([_|T], R):-
    intercl_et(T, R).



















