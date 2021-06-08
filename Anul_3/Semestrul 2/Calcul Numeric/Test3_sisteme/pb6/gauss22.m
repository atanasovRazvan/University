function [x,y] = gauss22(a, b, m, c, d, n)
    
    % ax + by = m (1)
    % cx + dy = n (2)
    % Pentru a scapa de x, inmultim (2) cu a/c, dupa care (1)-(2)
    
    factor = a / c;                                 % 1 FLOPS
    
    % Ecuatia devine (b-factor*d)y = m-factor*n
    y = (m - factor * n) / (b - factor * d);        % 5 FLOPS
    
    % inlocuim x in prima ecuatie
    x = (m - b * y) / a; % 3
    
    %total:                                         9 FLOPS
    
end