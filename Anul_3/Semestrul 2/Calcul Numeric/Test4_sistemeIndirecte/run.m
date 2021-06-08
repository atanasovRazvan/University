[A,b] = generateMatrixAndVector(100);
    
[GaussSolution, GaussIterations] = Gauss_Seidel(A,b);
testing = norm(b-A*GaussSolution) / norm(b) < cond(A) * 1e-6;
assert(testing == 1);
    
[JacobiSolution, JacobiIterations] = Jacobi(A,b);
testing = norm(b-A*JacobiSolution) / norm(b) < cond(A) * 1e-6;
assert(testing == 1);
    
[SORSolution, SORIterations] = SOR(A,b,1);
testing = norm(b-A*SORSolution) / norm(b) < cond(A) * 1e-6;
assert(testing == 1);