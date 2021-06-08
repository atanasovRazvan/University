function x=forwardsubsttr(L,b,trace)
%FORWARDSUBST - forward substitution
%L - lower triangular matrix
%b - right hand side vector

if nargin<3
    trace=0;
end
x=zeros(size(b));
n=length(b);
for k=1:n
    x(k)=(b(k)-L(k,1:k-1)*x(1:k-1))/L(k,k);
    if trace
        fprintf('x(%d)=%+f\n', k, x(k))
    end
end