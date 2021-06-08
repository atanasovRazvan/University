function y = riemann(f,a,b,n)
    integralF = exp(1) - 1;
    deltax = (b-a)/n;
    left = b-deltax;
    xstar = a:deltax:left;
    y = sum(feval(f,xstar).*deltax);
    fprintf('Reimann sum: %.15f\n', y);
    fprintf('Absolute err: %.15f\n', abs(integralF-y));
end