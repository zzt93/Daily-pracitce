#include <iostream>
#include "union.hpp"

using std::cout;
using std::endl;

/*
  Implement it using quick find algorithm
*/
Union::Union(unsigned int n){
    size = n;
    a = new int[n];
    for (unsigned int i = 0; i < size; ++i) {
        a[i] = i;
    }
}
inline int Union::find(int p){
    return a[p];
}

inline bool Union::connected(int p, int q){
    return a[p] == a[q];
}

void Union::unio(int p, int q){
    if (connected(p, q)){
        return;
    }
    int aim = find(p);
    int src = find(q);
    for (unsigned int i = 0; i < size; ++i) {
        if (a[i] == aim){
            a[i] = src;
        }
    }

}

int main(int argc, char *argv[])
{
    Union u(10);
    u.unio(5, 0);
    u.unio(3, 0);
    cout << u;
    u.unio(3, 8);
    u.unio(5, 9);
    cout << u;
    u.unio(0, 7);
    u.unio(5, 4);
    
    cout << u;
    return 0;
}
