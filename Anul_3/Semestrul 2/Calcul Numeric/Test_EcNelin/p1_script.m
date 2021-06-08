f = @(x) 2.*x.*besselj(1,x)-besselj(0,x);
fd = @(x) 3.*besselj(1, x) - 2.*x.*(besselj(1, x)./x - besselj(0, x));

ea = 0;
er = 1e-9;

x0 = [1;1;1];
[z,ni] = newtonmet(f,fd,x0,ea,er)

x0 = [0,0,0]; % valori de start
x1 = [1,1,1];
[z1,ni1] = secantmet(f,x0,x1,ea,er)

% afisare grafic si solutii
fplot(f,[0,10]); grid on; hold on;
plot(z,[0,0,0],'r*'); hold on;
plot(z1,[0,0,0],'g*');
legend('functia', 'solutii newton', 'solutii secanta')