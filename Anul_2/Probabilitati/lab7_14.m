function [p1, p2] = lab7_14(x, a)
n = length(x);
z = norminv(1-a/2,0,1);
xn = mean(x);
s = sqrt(xn * (1 - xn));
p1 = xn - z*s/sqrt(n);
p2 = xn + z*s/sqrt(n);