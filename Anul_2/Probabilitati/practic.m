function practic
  
  nrMax10 = 0;
  nrXMic3 = 0;
  V = [];
  
  for i = 1:500
    Y = [];
    
    for j = 1:10
      nr = unidrnd(5);
      X = 0;
      while nr != 5
        X = X+1;
        nr = unidrnd(5);
      end
      Y = [Y, X];
      if X<3
        nrXMic3 = nrXMic3+1;
      endif
    endfor
    
    Y
    V = [V, mean(Y)];
    
    if max(Y) < 10
      nrMax10 = nrMax10 + 1;
    endif
      
  endfor
  
  probabilitate_B = nrMax10/ 500
  probabilitate_C = nrXMic3 / 5000
  E = mean(V)
  fprintf("Distributie discreta uniforma");
  
  
  fplot(nrXMic3/5000);
  
endfunction
