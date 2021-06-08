function [z, Q] = double_nodes_diff(x, y, dy)
  %x - puncte stiute
  %y - f(x)
  %z nodurile dublate
  %Q - tabel de diferente divizate
  %folosit la interpolarea hermite
  
  m = length(x);
  
  for i = 1:m
    z(2*i-1) = x(i);
    z(2*i) = x(i);
    
    Q(2*i - 1, 1) = y(i);
    Q(2*i, 1) = y(i);
    
    Q(2*i, 2) = dy(i);
    
    if(i > 1)
      Q(2*i - 1, 2) = (Q(2*i - 1, 1) - Q(2*i -2, 1)) / (z(2*i - 1) - z(2*i - 2));
    end
  end
  
  for i =3:2*m
    for j = 3:i
      Q(i, j) = (Q(i, j-1) - Q(i - 1, j - 1))/(z(i) - z(i -j + 1)); 
    end
  end
  Q = diag(Q)';
end