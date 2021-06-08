a = -pi;
b = pi;
xx = linspace(a, b, 200);
n = 10;
f = @(x) x + sin(x .^ 2);
x = poly_chebyshev_1(a,b,n);
y = f(x);

rng = [1:n];
E = [ones(size(x')), x'.^rng];

c = E\y';

yy_cheb = [ones(size(xx')), xx'.^rng]*c;

figure(2);
hold on;
plot(x, y, 'o');
plot(xx, f(xx));
plot(xx, yy_cheb);
legend('nodes', 'f(x)', 'lsq');