#include <iostream>

class Union {
    int *a;
    unsigned int size;
    unsigned int component;
    int find(int p);
public:
    Union(unsigned int n);
    Union(Union&);
    ~Union(){
        delete[] a;
    }
    Union& operator = (Union&);

    friend std::ostream& operator << (std::ostream& o, const Union& u){
        o << "size: " << u.size << "component: "<< u.component << std::endl;
        for (unsigned int i = 0; i < u.size; ++i) {
            o << u.a[i] << " ";
        }
        o << std::endl;
        return o;
    }
    
    bool connected(int p, int q);
    void unio(int p, int q);
    unsigned int parts(){
        return component;
    }
    // for minimum cut problem
    int father(int p){
        return find(p);
    }
    int ith_size(int i);
    void reset();
    unsigned int size_of(){
        return size;
    }
};