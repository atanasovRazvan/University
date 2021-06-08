a = -2*pi;
b = 2*pi;
x = linspace(a, b, 200);
f = @(x) x.^2 .* sin(x);
y = f(x);
err = 1e-6;


m_echidist = 1;
xx = linspace(a, b, m_echidist);
w_echidist = weights_echidist(xx);
yy_echi = barycentricInterpolation(xx, f(xx), x, w_echidist);

y_norm = norm(y);

while norm(yy_echi - y)/y_norm > err
  m_echidist = m_echidist + 1;
  xx = linspace(a, b, m_echidist);
  w_echidist = weights_echidist(xx);
  yy_echi = barycentricInterpolation(xx, f(xx), x, w_echidist);
end

fprintf('Interpolare lagrange baricentrica, noduri echidistante, nr_noduri necesare: %d\n', m_echidist);

m_che = 1;
xx = poly_chebyshev_2(a, b, m_che);
w_che = weights_chebyshev(m_che);
yy_che = barycentricInterpolation(xx, f(xx), x, w_che);

while norm(yy_che - y)/y_norm > err
  m_che = m_che + 1;
  xx = poly_chebyshev_2(a, b, m_che);
  w_che = weights_chebyshev(m_che);
  yy_che = barycentricInterpolation(xx, f(xx), x, w_che);
end

fprintf("Interpolare lagrange baricentrica, noduri Cebisev, nr_noduri necesare: %d\n", m_che);