// cpp_compiler_options_openmp.cpp

#define MAX 50000

#include <omp.h>
#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <iostream>
#include <chrono>

using namespace std;

int main() {
    int tid, nthreads;
    int a[MAX], b[MAX], c[MAX], suma=0;
    int sumaThread[5] = { 0 };
    int i;

    for (i = 0; i < MAX; i++) {
        a[i] = 1;
        b[i] = 1;
    }
    
    auto startS = chrono::steady_clock::now();
    for (i = 0; i < MAX; i++)
        suma += a[i] * b[i];
    auto endS = chrono::steady_clock::now();
    cout << "Secvential: " << chrono::duration <double, std::milli>(endS - startS).count()<<endl;
    suma = 0;


    auto start = chrono::steady_clock::now();
    omp_set_num_threads(4);

    #pragma omp parallel default(shared) private(i, sumaThread, tid)
    {   
        tid = omp_get_thread_num();
        #pragma omp sections
        {
            #pragma omp section
            {
                // #pragma omp for schedule(dynamic, 10) reduction(+:suma)
                for (i = 0; i < MAX; i++)
                    suma += a[i] * b[i];
            }
            #pragma omp section
            {
                // #pragma omp for schedule(dynamic, 10)
                for (i = 0; i < MAX; i++)
                    c[i] = a[i] + b[i];
            }
        }
        
        // DATA RACE: suma += sumaThread[tid];
        #pragma omp critical
        {
            suma += sumaThread[tid];
        }

    }
    //for (int k = 0; k < omp_get_num_threads(); k++) suma += sumaThread[k];

    auto end = chrono::steady_clock::now();
    cout << "Paralel: " << chrono::duration <double, std::milli>(end - start).count();
    cout << '\n';

    return 0;
}