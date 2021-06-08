function [A,b] = generateMatrixAndVector(n)
    % Construim vectorul b
    % 1 0 ... 0 1
    b = zeros(n, 1);
    b(1) = 1;
    b(n) = 1;
    
    % Construim matricea A
    % diagonala principala [2 2 ... 2]
    % diagonala sub-principala si super-principala [-1 -1 ... -1]
    e = ones(n,1);
    A = spdiags([-e 2*e -e],-1:1,n,n);
    A = full(A);

end