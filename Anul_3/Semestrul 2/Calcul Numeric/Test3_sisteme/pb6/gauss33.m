function [x, y, z] = gauss33(a, b, c, m, d, e, f, n, g, h, i, o)
    
    % ax + by + cz = m
    % dx + ey + fz = n
    % gx + hy + iz = o
    
    fact1 = a/d;                        % 1 FLOPS
    fact2 = a/g;                        % 1 FLOPS
    
    % Vom recalcula coeficientii
    % d si g dispar dupa scaderi
    e = b - e*fact1;                    % 2 FLOPS
    f = c - f*fact1;                    % 2 FLOPS
    n = m - n*fact1;                    % 2 FLOPS
    
    h = b - h*fact2;                    % 2 FLOPS
    i = c - i*fact2;                    % 2 FLOPS
    o = m - o*fact2;                    % 2 FLOPS
    
    % Asemanator, recalculam factorul pentru a reduce ultima ecuatie la una
    % cu o singura necunoscuta
    fact3 = e / h;                      % 1 FLOPS
    
    % Recalculam factorii dupa scadere, h dispare
    i = f - i*fact3;                    % 2 FLOPS
    o = n - o*fact3;                    % 2 FLOPS
    
    % Il calculam pe z si apoi inlocuim in celelalte ecuatii
    z = o / i;                          % 1 FLOPS
    y = (n - f*z) / e;                  % 3 FLOPS
    x = (m - c*z - b-y) / a;            % 5 FLOPS

    %total                              % 28 FLOPS
    
end