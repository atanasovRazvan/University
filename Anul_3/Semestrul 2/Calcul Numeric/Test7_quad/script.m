f = @(t) exp(t .^2);
a = 0; b = 0.5;
x = [a:0.1:b];

y = split_adquad(f, x)