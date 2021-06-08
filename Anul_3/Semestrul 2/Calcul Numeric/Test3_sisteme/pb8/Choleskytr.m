function R=Choleskytr(A,trace)
%CHOLESKY - Cholesky factorization
%call R=Cholesky(A)
%A - HPD matrix 
%R - upper triangular matrix

if nargin<2, trace=0; end
[m,n]=size(A);
if trace, disp(A); end
for k=1:m
    if trace
        fprintf('step %d:\n', k);
    end
    if A(k,k)<=0
        error('matrix is not HPD')
    end
    for j=k+1:m
        A(j,j:m)=A(j,j:m)-A(k,j:m)*A(k,j)/A(k,k);
    end
    A(k,k:m)=A(k,k:m)/sqrt(A(k,k));
    if trace, disp(triu(A)); end
end
R=triu(A);