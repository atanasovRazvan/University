function [nodes, coeff] = script_b(u)
% u - valoarea miu din w(t)
% nodes, coeff - valorile nodurilor si coeficientilor

a = sym(-Inf);
b = sym(Inf);
syms t;

% Definim eroarea 10^-8
err = 1e-8;

% Pornim cu n=1
n=1;

% Repetam pana cand restul devine mai mic ca eroarea;
% Astfel, obtinem n-ul
while 1
   R = vpa(1/factorial(2*n)*int((abs(t)^(2*u))*exp(-t^2)*hermiteH(n,t)^2,t,a,b));
    if R<err
        break;
    end
    n = n+1;
end

% Ne folosim de rutina Gauss Hermite (care de fapt nu este chiar Gauss_Hermite 
% datorita faptului ca alfa si beta au forme diferite de cele din laborator)
% pentru a obtine valorile nodurilor si coeficientilor
[nodes, coeff] = Gauss_Hermite(n, u);

end