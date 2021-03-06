DelMember(_, [], []).
delMember(X, [X|Xs], Y) :-
    delMember(X, Xs, Y).
delMember(X, [T|Xs], [T|Y]) :-
    dif(X, T),
    delMember(X, Xs, Y).

%Sa se scrie un predicat care transforma o lista intr-o multime, in
%ordinea primei aparitii. Exemplu: [1,2,3,1,2] e transformat in [1,2,3].
%multime(L:list, R:list)
%L: lista initiala cu elemente
%R: lista rezultat care devine multime
%model de flux (i, i), (i, o)

multime([], []):-!.
multime([H|T], [H|M]):-
    delMember(H, T, S),
    !,
    multime(S, M).

% Sa se scrie o functie care descompune o lista de numere intr-o lista de
% forma [ lista-de-numere-pare lista-de-numere-impare]
% pareimpare(L: list, R: list)
% L: lista initiala cu elemente
% R: lista rezultat formata din doua liste
% model de flux (i, i), (i, o)

pareimpare([], [[],[]]):-!.

pareimpare([H|T], [[H|X], Y]):-
    0 is H mod 2,
    !,
    pareimpare(T, [X, Y]).

pareimpare([H|T], [X, [H|Y]]):-
    1 is H mod 2,
    !,
    pareimpare(T, [X,Y]).
