function y = Test4_Prob8
    f = @(x) exp(x);
    n = 5000;
    pas = 5000;
    while n <= 100000
        fprintf('N: %d\n', n); 
        y = riemann(f, 0, 1, n);
        n = n + pas;
    end
end