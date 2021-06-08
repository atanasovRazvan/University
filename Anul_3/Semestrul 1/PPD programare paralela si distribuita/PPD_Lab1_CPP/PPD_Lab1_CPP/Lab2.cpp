//
// Created by Virginia Niculescu on 03/10/16.
// ~ a small initial example with C++ threads
//


#include <iostream>
#include <vector>
#include <thread>
#include <chrono>

using namespace std;


void add(vector<int> a, vector<int> b, vector<int> &c, int start, int step, int N) {
    
    for (int i = start; i < N; i += step)
        c[i] = a[i] + b[i];

}

int main() {
   

    int N = 10;
    int p = 4;

    vector<int> a, b, c(N);

    for (int i = 0; i < N; i++) {
        a.push_back(i);
        b.push_back(i);
        //c.push_back(0);
    }

    vector<thread> threads;

    for (int i = 0; i < p; i++) {
        threads.push_back(thread(add, a, b, ref(c), i, p, N));
    }

    for (int i = 0; i < p; i++) {
        threads[i].join();
    }

    for (int i = 0; i < N; i++) {
        cout << c[i] << " ";
    }

    return 0;

}