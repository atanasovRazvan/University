function y = split_adquad(f, x)
  n = length(x);
  int = 0;
  y = zeros(1, n);

  for i=1:n
    y(i) = exp(-x(i)^2) * int;
    if i == n
      break;
    end
    int = int + adquad(f, x(i), x(i+1));
  end
  
end