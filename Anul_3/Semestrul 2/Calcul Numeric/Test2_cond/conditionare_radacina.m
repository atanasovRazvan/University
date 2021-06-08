  function conditionare = conditionare_radacina(poly, root)
    warning('off');
    % polynomial looks like ax^n + bx^n-1 + ... + c
    % parameter poly as [a b ... c]
    % poly = [1 -2 4/3 -8/27];
    % deriv_p = [3 -4 4/3];
    powers = [length(poly) - 1:-1:1];
    deriv_p = powers .* poly(1: end-1);
    conditionare = abs((polyval(deriv_p, root).*root)./polyval(poly, root));
  end