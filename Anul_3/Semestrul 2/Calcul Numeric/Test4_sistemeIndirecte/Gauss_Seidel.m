function [x,ni]=Gauss_Seidel(A,b,err)
%A - matricea sistemului
%b - vectorul termenilor liberi
%err - toleranta (implicit 1e-6)
%x - solutia
%ni -numarul de iteratii realizat efectiv
if nargin < 3 
   err=1e-6;
end

[m,n]=size(A);
if (m~=n) || (n~=length(b))
   error('dimensiuni ilegale')
end

x=zeros(n,1);
M=tril(A);
N=M-A;
i = 1;
while true
   xn=M\(N*x+b);
   if norm(xn-x,inf) < err*norm(xn,inf)
      x=xn;
      ni=i;
      return
   end
   x=xn;
   i = i + 1;
end