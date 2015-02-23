#ifndef __DIGRAPH_HPP__
#define __DIGRAPH_HPP__




#include <iostream>
#include <vector>
#include <map>
#include <set>
#include "vertex.hpp"
#include "edge.hpp"
#include "union/improved_union.cpp"
#include "utility/reader.cpp"

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
    void add_edge(Edge<K, V>* const e){
        edges.push_back(e);
    }
    bool add_vertex(Vertex<K, V>* const v){
        if (has(v)) {
            return false;
        }
        vertices[v->key()] = v;
        return true;
    }
    
    Union connect;
    void find_finish(vector<K>& f);
    void count_finish(vector< Vertex<K, V>*>& v, vector<K>& f);
    void analyse_part(vector< Vertex<K, V>*>& v);
    void finish_loop(vector<K>& f, vector<int> size);
    void finish_loop(vector<K>& f);
    void reverse_edges();
    template <class O>
    void dfs_loop(Vertex<K, V>*, O);
public:
    Digraph(){
    }
    Digraph(unsigned int size): connect(size) {
    }
    //
    template <class O>
    void all_vertices(O o) const{
        for (auto& v: vertices) {
            *o++ = v.second;
        }
    }
    
    bool has(const Vertex<K, V>* v) const{
        if (vertices.find(v->key()) == vertices.end()){
            return false;
        }
        return true;
    }
    void add_edge(K tail, K head) {
        //assert(( cout << " head is " << head << " tail is " << tail << endl, head < vertices.size() && tail < vertices.size()));
        Edge<K, V> *e = new Edge<K, V>(vertices[tail], vertices[head]);
        add_edge(e);
    }
    
    /*
      args: {:}
      return value: the number of vertices you added this time
      Usage:
    */
    int add_vertices(const vector< Vertex<K, V>* >& vs){
        int o_size = vertices.size();
        for (unsigned int i = 0;i < vs.size(); ++i) {
            add_vertex(vs[i]);
        }
        int gap = vertices.size() - o_size;
        return gap;
    }
    void add_vertex(K k, V v) {
        Vertex<K, V> *ve = new Vertex<K, V>(k, v);
        add_vertex(ve);
    }
    void add_edges(const vector< Edge<K, V>* >& es){
        for (unsigned int i = 0;i < es.size(); ++i) {
            add_edge(es[i]);
        }
    }
    void set_edge_istream(Reader&);


    // functionalities
    template <class O>
    void dfs(O, K);

    template <class O>
    void scc_size(O);
    template <class O>
    void scc_content(O);
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
    //clear the search_set for next iteration
    search_set.clear();
}

template <class K, class V>
template <class O>
void Digraph<K, V>::dfs(Vertex<K, V>* v, O o){
    //cout << v->key() << endl;
    assert(v != NULL && "pointer is null");
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

template <class K, class V>
template <class O>
void Digraph<K, V>::dfs_loop(Vertex<K, V>* v, O o){
    assert(v != NULL && "pointer is null");
    if (search_set.find(v->key()) != search_set.end()){//check whether it is travelled
        return;
    }
    *o++ = v;
    search_set.insert(v->key());//set traversal true;
    
    v->restart();// make the iterator has been correctly set
    while (v->has_next()){
        dfs_loop(v->next(), o);
    }
    *o++ = NULL;
}

/*
  args: {edges: the matrix of edges}
  return value: a graph or NULL if input invalid
  Usage:
*/
template <class K, class V>
Digraph<K, V>* set_graph(vector< vector<K> > edges, vector<V> v){
    //check whether it is a matrix
    for (unsigned int i = 0; i < edges.size(); ++i) {
        if (edges.size() != edges[i].size()){
            return NULL;
        }
    }

    for (unsigned int i = 0; i < edges.size(); ++i) {
        
    }
}


#endif /* __DIGRAPH_HPP__ */