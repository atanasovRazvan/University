function [g_nodes,g_coeff]=Gauss_Hermite(n, u)
    % Gauss-Hermite nodes and coefficients
    beta=zeros(n);
    beta(1) = gamma(u+1/2);
    for k = 2:2:n
        beta(k) = 1/2*k+u;
    end
    for k = 3:2:n
        beta(k) = 1/2*k;
    end
    alpha=zeros(n,1);
    [g_nodes,g_coeff]=gaussquad(alpha,beta);
end