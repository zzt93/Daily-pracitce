#include <iostream>

class Union {
    int *a;
    unsigned int size;
    int find(int p);
public:
    Union(unsigned int n);
    ~Union(){
        delete[] a;
    }

    friend std::ostream& operator << (std::ostream& o, const Union& u){
        for (unsigned int i = 0; i < u.size; ++i) {
            o << u.a[i] << " ";
        }
        o << std::endl;
        return o;
    }
    
    bool connected(int p, int q);
    void unio(int p, int q);
};