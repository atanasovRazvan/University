function spd=generateMatrix(n)

  A = rand(n, n);
  trA = transpose(A);
  spd = A * trA;
  
end