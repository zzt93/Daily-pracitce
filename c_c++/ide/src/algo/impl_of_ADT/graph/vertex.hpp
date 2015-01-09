#ifndef _my_vertex
#define _my_vertex

#include <iostream>
#include <vector>
//#include <functional>
#include "edge.hpp"

using std::vector;
//using std::reference_wrapper;
using std::cout;
using std::endl;

template <class K, class V>
class Vertex {
private:
    K m_key;
    V m_value;
    vector< Vertex<K, V>* > adjacent;
    vector< Edge<K, V>* > in;
    vector< Edge<K, V>* > out;
    
    typedef typename vector< Vertex<K, V>* >::iterator vit;
    vit adj;
    
public:
    Vertex(){}
    Vertex(K k, V v): m_key(k), m_value(v) {
    }

    /* to set the adjacent vertices and
       connected edges */
    /* to use reference_wrapper, you can't use
       const .., for you change the const to non-const */
    void set_adj(Vertex<K, V>* v){
        //        cout << "in " << m_key << " push " << v->key() << endl;
        adjacent.push_back(v);
    }
    void set_in(Edge<K, V>* e){
        in.push_back(e);
    }
    void set_out(Edge<K, V>* e){
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
    
};

#endif