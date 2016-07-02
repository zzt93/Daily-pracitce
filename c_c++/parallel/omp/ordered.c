#include <iostream>
#include <vector>

int main() {
    int n = 10;
    vector<int> v;

#pragma omp parallel for ordered schedule(static, 1)
    for (int i = 0; i < n; ++i){
        // some computation
#pragma omp ordered
        v.push_back(i);
    }
}