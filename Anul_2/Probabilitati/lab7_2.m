function lab7_2
m=160; sigma=10; n=300;
x=normrnd(m, sigma, 1, n);
[m1, m2] = lab7_11(x, 0.05, 10)
[m1, m2] = lab7_12(x, 0.05)
[v1, v2] = lab7_13(x, 0.05)
[p1, p2] = lab7_14(155<x&x<165, 0.05)