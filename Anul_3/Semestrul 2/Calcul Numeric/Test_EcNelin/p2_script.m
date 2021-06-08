ea = 0;
er = 1e-3;

x0 = [1, 1, 1]';

f = @(x) [
        x(1)*exp(x(2))+x(3)-10;
        x(1)*exp(2*x(2))+2*x(3)-12; 
        x(1)*exp(3*x(2))+3*x(3)-18
    ];

# fjacob = @(x) [exp(x(2)), x(1)*exp(x(2)), 1;
#           exp(2*x(2)), 2*x(1)*exp(2*x(2)), 2;
#           exp(3*x(2)), 3*x(1)*exp(3*x(2)), 3];

fd = @(x) [
        x(1)*x(2)*exp(1)+x(3);
        x(1)*x(2)*exp(2)+x(3);
        x(1)*x(2)*exp(3)+x(3)
    ];

[z,ni] = newtonmet(f,fd,x0,ea,er, 1000)
if length(z) == 3,
    alpha = z(1)
    beta = z(2)
    gamma = z(3)
end