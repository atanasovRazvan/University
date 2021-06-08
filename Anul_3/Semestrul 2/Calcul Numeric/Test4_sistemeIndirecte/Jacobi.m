function [x,ni]=Jacobi(A,b,err)
%A - system matrix
%b - right hand side vector
%err - tolerance (default 1e-6)
%x - solution
%ni -number of actual iterations

%parameter check
if nargin < 3, err=1e-6; end
x0 = zeros(size(b));
[m,n]=size(A);
if (m~=n) || (n~=length(b))
   error('ilegal size')
end

M=diag(diag(A));
N=M-A;
x=x0(:);
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