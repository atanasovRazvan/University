a=-pi;
b=pi;
xx = linspace(a, b, 200);
n = 10;

f = @(x) x + sin(x .^ 2 );
df = @(x) 1 + 2 .* x.* cos(x .^ 2 );
ddf = @(x) 2 .* cos(x .^ 2 ) - 4 .* x .^2 .* sin(x .^ 2);

x = poly_echidistant(a, b, 10);
y = f(x);

aa = CubicSplinec(x, y, 0, [df(a), df(b)]);
yy_complete = evalsplinec(x, aa, xx);

bb = CubicSplinec(x, y, 1, [ddf(a), ddf(b)]);
yy_ord2 = evalsplinec(x, bb, xx);

c = CubicSplinec(x, y, 2);
yy_natural = evalsplinec(x, c, xx);

d = CubicSplinec(x, y, 3);
yy_boor = evalsplinec(x, d, xx);

figure(1);
hold on;
plot(x, y, 'o');
plot(xx, f(xx));
plot(xx, yy_complete);
plot(xx, yy_ord2);
plot(xx, yy_natural);
plot(xx, yy_boor);

legend('noduri echidist', 'f(x)', 'complete', 'ord 2 der', 'natural', 'deBoor', 'Location', 'BestOutside');
x = poly_chebyshev_2(a, b, n);

y = f(x);

aa = CubicSplinec(x, y, 0, [df(a), df(b)]);
yy_complete = evalsplinec(x, aa, xx);

bb = CubicSplinec(x, y, 1, [ddf(a), ddf(b)]);
yy_ord2 = evalsplinec(x, bb, xx);

c = CubicSplinec(x, y, 2);
yy_natural = evalsplinec(x, c, xx);

d = CubicSplinec(x, y, 3);
yy_boor = evalsplinec(x, d, xx);

figure(2);
hold on;
plot(x, y, 'o');
plot(xx, f(xx));
plot(xx, yy_complete);
plot(xx, yy_ord2);
plot(xx, yy_natural);
plot(xx, yy_boor);

legend('noduri cebisev', 'f(x)', 'complete', 'ord 2 der', 'natural', 'deBoor', 'Location', 'BestOutside');