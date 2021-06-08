function [x,ni]=SOR(A,b,omega,err)
%A - system matrix
%b - right hand side vector
%omega - relaxation parameter
%err - tolerance (default 1e-3)
%x - solution
%ni -actual number of iterations

if nargin < 4, err=1e-6; end
if (omega<=0) || (omega>=2)
    error('ilegal relaxation parameter')
end

x0=zeros(size(b));
[m,n]=size(A);
if (m~=n) || (n~=length(b))
    error('ilegal size')
end

M=1/omega*diag(diag(A))+tril(A,-1);
N=M-A;
x=x0;
i = 1;
while true
    x0=x;
    x=M\(N*x0+b); 
    if norm(x-x0,inf)<err*norm(x,inf)
        ni=i;
        return
    end
    i = i + 1;
end