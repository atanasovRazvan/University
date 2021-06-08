function [x,y] = solveCholesky(n)
    spd = generateMatrix(n);
    b = 1:n;
    b = transpose(b);
    R = Cholesky(spd);
    y = forwardsubsttr(R, b);
    x = backsubsttr(R', y);
    
    % Verificare
    testing = norm(b - spd * x) \ norm(b) < cond(spd) * 1.0e+06;
    assert(testing==1);
end