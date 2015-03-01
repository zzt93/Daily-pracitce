#ifndef _my_dirction_edge
#define _my_dirction_edge

#include "vertex.hpp"
//#include <functional>
#include <climits>

template <class K, class V>
class Vertex;

//using std::reference_wrapper;


template <class K, class V>
class Edge {
private:
    /* the element in the container must be
    Assignable, we can have vector<int&> here
    or vector<const int> */
    
    Vertex<K, V>* one_side;
    Vertex<K, V>* ano_side;
    int weight;

    // to represent the infinite
    static const int inf = INT_MAX;//std::numeric_limits<int>::max();
    void setV(Vertex<K, V>* o, Vertex<K, V>* a, int w=1) {
        o->set_adj(a);
        o->set_out(this);
        a->set_in(this);
        weight = w;
    }
public:
    Edge(Vertex<K, V>* o, Vertex<K, V>* a, int w=1)
        : one_side(o), ano_side(a),  weight(w){
        setV(o, a, w);
    }

    /* const functions */
    Vertex<K, V>* one () const{
        return one_side;
    }
    Vertex<K, V>* another() const {
        return ano_side;
    }
    int wei() const {
        return weight;
    }

    void reverse() {
        one_side->remove_adj(ano_side);
        one_side->remove_out(this);
        ano_side->remove_in(this);
        setV(ano_side, one_side);
    }
};

#endif