#include <iostream>
#include <vector>
#include <map>
#include <set>
#include "vertex.hpp"
#include "edge.hpp"

using std::vector;
using std::map;
using std::set;
using std::cout;
using std::endl;


template <class K, class V>
class Digraph {
private:
    map< K, Vertex<K, V>* > vertices;
    vector< Edge<K, V>* > edges;

    set<K> search_set;
    template <class O>
    void dfs(Vertex<K, V>*, O);
public:
    Digraph(){
    }
    
    //
    template <class O>
    void all_vertices(O o) const{
        for (auto& v: vertices) {
            *o++ = v.second;
        }
    }
    
    bool add_vertex(Vertex<K, V>* const v){
        if (has(v)) {
            return false;
        }
        vertices[v->key()] = v;
        return true;
    }
    bool has(const Vertex<K, V>* v) const{
        if (vertices.find(v->key()) == vertices.end()){
            return false;
        }
        return true;
    }
    void add_edge(Edge<K, V>* const e){
        edges.push_back(e);
    }
    int add_vertices(const vector< Vertex<K, V>* >& vs){
        int o_size = vertices.size();
        for (unsigned int i = 0;i < vs.size(); ++i) {
            add_vertex(vs[i]);
        }
        int gap = vertices.size() - o_size;
        return gap;
    }
    void add_edges(const vector< Edge<K, V>* >& es){
        for (unsigned int i = 0;i < es.size(); ++i) {
            add_edge(es[i]);
        }
    }


    // functionalities
    template <class O>
    void dfs(O, K);
};

/*
  invoke the depth-first-search from the
  vertex of key is `k`
*/
template <class K, class V>
template <class O >
void Digraph<K, V>::dfs(O o, K k){
    if (vertices.find(k) == vertices.end()){
        cout << "no such vertex now" << endl;
        return;
    }
    Vertex<K, V>* first = vertices.find(k)->second;
    dfs(first, o);
}

template <class K, class V>
template <class O>
void Digraph<K, V>::dfs(Vertex<K, V>* v, O o){
    //cout << v->key() << endl;
    *o++ = v;
    if (search_set.find(v->key()) != search_set.end()){//check whether it is travelled
        *o++ = NULL;
        return;
    }
    search_set.insert(v->key());//set traversal true;
    
    v->restart();// make the iterator has been correctly set
    while (v->has_next()){
        dfs(v->next(), o);
    }
    *o++ = NULL;
}