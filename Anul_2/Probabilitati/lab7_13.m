function [v1, v2] = lab7_13(x, a)
n = length(x);
c1 = chi2inv(1-a/2, n-1);
c2 = chi2inv(a/2, n-1);
s2 = var(x);
v1 = (n-1)*s2/c1;
v2 = (n-1)*s2/c2;