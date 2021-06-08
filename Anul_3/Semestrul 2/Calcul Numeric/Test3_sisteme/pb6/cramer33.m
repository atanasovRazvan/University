  
function [x,y,z]=cramer33(a, b, c, m, d, e, f, n, g, h, i, o)
    
    %ax + by + cz = m
    %dx + ey + fz = n
    %gx + hy + iz = o
    
    % determinants
    delta = a*(e*i-h*f)-b*(d*i-g*f)+c*(d*h-g*e); % 14
    
    % without applying distributivity, 17 flops per determinant
    
    % m b c
    % n e f
    % o h i
    deltaX = m*(e*i-h*f)+b*(o*f-n*i)+c*(n*h-e*o); % 14
    % a m c
    % d n f
    % g o i
    deltaY = a*(n*i-o*f)+m*(f*g-d*i)+c*(d*o-g*n); % 14
    % a b m
    % d e n
    % g h o
    deltaZ = a*(e*o-h*n)+b*(n*g-d*o)+m*(d*h-g*e); % 14
    
    x = deltaX/delta; % 1
    y = deltaY/delta; % 1
    z = deltaZ/delta; % 1
    
    % total:           59 FLOPS
end