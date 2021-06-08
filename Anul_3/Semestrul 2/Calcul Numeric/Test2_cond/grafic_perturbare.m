function grafic_perturbare(poly)
    % polynomial looks like ax^n + bx^n-1 + ... + c
    % parameter poly as [a b ... c]
    % poly = [1 -2 4/3 -8/27];
    hold on
    for m=1:100
        % generating N(0, 10^-5)
        normal_dis=normrnd(0,10^(-5), [1,length(poly)]);
        % perturbating polynomial
        perturbed_poly=poly + normal_dis;
        % get roots
        perturbed_roots=roots(perturbed_poly);
        % draw roots
        plot_roots=plot(perturbed_roots,'k.');
        set(plot_roots,'Markersize', 5, 'color', 'blue')
    end
end