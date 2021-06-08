function [m1, m2] = lab7_12(x, a)
n = length(x);
t = tinv(1-a/2, n-1);
s = std(x);
m1 = mean(x)-t*s/sqrt(n);
m2 = mean(x)+t*s/sqrt(n);