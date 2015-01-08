#ifndef _my_vertex
#define _my_vertex

#include <iostream>
#include <vector>
#include "edge.hpp"

using std::vector;

template <class K, class V>
class Vertex {
private:
    K m_key;
    V m_value;
    vector< Vertex<K, V> > adjacent;
    vector< Edge<K, V> > in;
    vector< Edge<K, V> > out;
    
    typedef typename vector< Vertex<K, V> >::iterator vit;
    vit adj;
    
public:
    Vertex(K k, V v): m_key(k), m_value(v) {
    }

    /* to set the adjacent vertices and
       connected edges */
    void set_adj(const Vertex<K, V>& v){
        adjacent.push_back(v);
    }
    void set_in(const Edge<K, V>& e){
        in.push_back(e);
    }
    void set_out(const Edge<K, V>& e){
        out.push_back(e);
    }
    /* const functions */
    K key() const {
        return m_key;
    }
    V value() const {
        return m_value;
    }
    /*
      0.invoke restart() to refresh your change
      1.invoke has_next() before next()
       or it will fail
      2. invoke restart() to re-iterate it
    */
    Vertex<K, V> next() {
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
    
};

#endif