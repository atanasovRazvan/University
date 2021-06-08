function S = Test4_Prob7(x)

    if x < 0
        sign = -1;
        x = -x;
    else
        sign = 1;
    end
    
    prevS = 0;
    S = 1;
    T = 1;
    n = 1;
    
    while S ~= prevS
        prevS = S;
        T = T * x / n;
        S = prevS + T;
        n = n + 1;
    end
    
    if sign == -1
        S = 1 / S;
    end
    
end