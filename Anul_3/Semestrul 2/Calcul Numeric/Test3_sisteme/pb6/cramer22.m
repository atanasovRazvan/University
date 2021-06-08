function [x, y] = cramer22(a, b, m, c, d, n)
    % ax + by = m
    % cx + dy = n
    
    % compute determinants
    delta = a*d - b*c; % 3
    
    % m b
    % n d
    deltaX = m*d - b*n; % 3
    
    % a m
    % c n
    deltaY = a*n - m*c; % 3
    
    x = deltaX / delta; % 1
    y = deltaY / delta; % 1
    
    %total:              11 FLOPS
end