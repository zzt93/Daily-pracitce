#ifndef _my_vertex
#define _my_vertex

#include <iostream>
#include <vector>
//#include <functional>
#include <algorithm>
#include <cassert>
#include "edge.hpp"

using std::vector;
//using std::reference_wrapper;
using std::cout;
using std::endl;
using std::find;

enum Visit {
    VISITED, AGAIN, NO_MORE_EDGE,
};

template <class K, class V>
class Vertex {
private:
    K m_key;
    V m_value;
    Visit state;
    vector< Vertex<K, V>* > adjacent;
    vector< Edge<K, V>* > in;
    vector< Edge<K, V>* > out;
    
    typedef typename vector< Vertex<K, V>* >::iterator vit;
    typename vector< Edge<K, V>*>::iterator out_it;
    vit adj;
    
public:
    Vertex(){}
    Vertex(K k, V v): m_key(k), m_value(v) {
    }

    /* to set the adjacent vertices and
       connected edges */
    /* to use reference_wrapper, you can't use
       const .., for you can't assign the const to non-const */
    void set_adj(Vertex<K, V>* v){
        adjacent.push_back(v);
    }
    void remove_adj(Vertex<K, V>* v) {
        auto a = find(adjacent.begin(), adjacent.end(), v);
        assert(a != adjacent.end());
        adjacent.erase(a);
    }
    void set_in(Edge<K, V>* e){
        in.push_back(e);
    }
    void remove_in(Edge<K, V>* e) {
        auto a = find(in.begin(), in.end(), e);
        assert(a != in.end());
        in.erase(a);
    }
    void set_out(Edge<K, V>* e){
        out.push_back(e);
    }
    void remove_out(Edge<K, V>* e) {
        auto a = find(out.begin(), out.end(), e);
        assert(a != out.end());
        out.erase(a);
    }

    Visit visit() const {
        return state;
    }
    void set_visit(Visit v) {
        state = v;
    }
    /* const functions */
    K key() const {
        return m_key;
    }
    V value() const {
        return m_value;
    }
    /*
      0.invoke restart() every time you change the vector
      1.invoke has_next() before next()
       or it will fail
      2. invoke restart() to re-iterate it
    */
    Vertex<K, V>* next() {
        //        cout << "in " << m_key << " get " << (*adj)->key() << " " << adjacent[0]->key() << endl;
        return *adj++;
    }
    bool has_next() const {
        if (adj == adjacent.end()){
            return false;
        }
        return true;
    }
    void restart() {
        adj = adjacent.begin();
    }
    
    void init_out() {
        out_it = out.begin();
    }
    bool has_out() const {
        if (out_it == out.end()) {
            return false;
        }
        return true;
    }
    Edge<K, V>* next_out() {
        return *out_it++;
    }
};

#endif
