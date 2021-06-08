% Punctul c
% Definim f(t)
f = @(t) cos(t);

% Obtinem nodurile si coeficientii din rutina de la punctul b
[nodes, coeff] = script_b(1);

% Obtinem aproximarea
aprox = coeff * f(nodes);

% Pentru 8 zecimale exacte
fprintf("I=%.8f\n", aprox);