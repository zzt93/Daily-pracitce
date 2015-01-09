#include <iostream>
#include <vector>
#include <map>
#include "vertex.hpp"
#include "edge.hpp"

using std::vector;
using std::map;
using std::cout;
using std::endl;


template <class K, class V>
class Digraph {
private:
    map< K, Vertex<K, V> > vertices;
    vector< Edge<K, V> > edges;

    map< K, bool > search_map;
    template <class O>
    void dfs(Vertex<K, V>*, O);
public:
    Digraph(){
    }
    
    //
    template <class O>
    void all_vertices(O o) const{
        for (auto v: vertices) {
            *o++ = v.second;
        }
    }
    
    bool add_vertex(const Vertex<K, V>& v){
        if (has(v)) {
            return false;
        }

        vertices[v.key()] = v;
        return true;
    }
    bool has(const Vertex<K, V>& v) const{
        if (vertices.find(v.key()) == vertices.end()){
            return false;
        }
        return true;
    }
    void add_edge(const Edge<K, V>& e){
        edges.push_back(e);
    }
    int add_vertices(const vector< Vertex<K, V> >& vs){
        int o_size = vertices.size();
        for (unsigned int i = 0;i < vs.size(); ++i) {
            add_vertex(vs[i]);
        }
        int gap = vertices.size() - o_size;
        return gap;
    }
    void add_edges(const vector< Edge<K, V> >& es){
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
    Vertex<K, V> first = vertices.find(k)->second;
    dfs(&first, o);
}

template <class K, class V>
template <class O>
void Digraph<K, V>::dfs(Vertex<K, V>* v, O o){
    //cout << v->key() << endl;
    *o++ = v;
    if (search_map.find(v->key()) != search_map.end()){//check whether it is travelled
        *o++ = NULL;
        return;
    }
    search_map[v->key()] = true;//set traversal true;
    
    v->restart();// make the iterator has been correctly set
    while (v->has_next()){
        dfs(v->next(), o);
    }
    *o++ = NULL;
}