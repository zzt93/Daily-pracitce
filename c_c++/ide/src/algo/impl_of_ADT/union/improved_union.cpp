#include <iostream>
#include "union.hpp"

using std::cout;
using std::endl;

/*
  Implement it using weighted quick union algorithm
  
  what is in the array store?
  case 1: root -- the -1*(size)
  case 2: other node -- its father
*/
Union::Union(unsigned int n){
    size = n;
    a = new int[size];
    for (unsigned int i = 0; i < size; ++i) {
        a[i] = -1;//the size = 1;
    }
}

int Union::find(int p){
    int root = p;
    int temp = a[p];
    while (temp >= 0){
        root = temp;
        temp = a[temp];
    }
    return root;
}

inline bool Union::connected(int p, int q){
    return find(p) == find(q);
}

void Union::unio(int p, int q){
    if (connected(p, q)){
        return;
    }
    int aim = find(p);
    int src = find(q);
    int size1 = -a[aim];
    int size2 = -a[src];
    // only when the second is large we will set it as root
    if (size1 < size2){
        // add the size
        a[src] = a[src] + a[aim];
        a[aim] = src;
    } else {
        a[aim] = a[src] + a[aim];
        a[src] = aim;
    }

}

int main(int argc, char *argv[])
{
    Union u(10);
    u.unio(5, 0);
    u.unio(3, 0);
    cout << u;
    u.unio(4, 9);
    u.unio(6, 9);
    cout << u;
    u.unio(0, 9);
    cout << u;
    u.unio(5, 4);
    
    cout << u;
    return 0;
}
