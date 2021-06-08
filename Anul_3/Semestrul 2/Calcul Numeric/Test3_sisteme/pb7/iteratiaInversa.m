function lambda = iteratiaInversa(n, noIterations)
    A = generateMatrix(n);
    x=rand(n,1);
    
    for k= 1:noIterations
        prevLambda = x'*A*x;
        x=A\x;
        x=x/norm(x);
        lambda = x'*A*x;
        %convergence
        if abs(lambda/prevLambda) < 1, break
    end
        
end