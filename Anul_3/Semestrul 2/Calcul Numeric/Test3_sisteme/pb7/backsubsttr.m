function x=backsubsttr(U,b,trace)
%BACKSUBST - backward substitution
%U - upper triangular matrix
%b - right hand side vector

if nargin<3, trace=1; end
n=length(b);
x=zeros(size(b));
for k=n:-1:1
    x(k)=(b(k)-U(k,k+1:n)*x(k+1:n))/U(k,k);
    if trace
        fprintf('x(%d)=%+f\n', k, x(k))
    end
end
