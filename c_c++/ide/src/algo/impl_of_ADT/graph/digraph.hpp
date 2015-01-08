#include <iostream>
#include <vector>
#include <map>
#include "vertex.hpp"
#include "edge.hpp"

using std::vector;
using std::map;

template <class K, class V>
class Graph {
private:
    map< K, Vertex<K, V> > vertices;
    vector< Edge<K, V> > edges;

public:
    Graph(){
    }
    Graph(Vertex<K, V> v, Edge<K, V> e)
        : vertices(v), edges(e){
    }

    bool add_vertex(Vertex<K, V> v){
        if (has(v)) {
            return false;
        }

        vertices[v.key()] = v;
        return true;
    }
    bool has(Vertex<K, V> v){
        if (vertices.find(v.key) == vertices.end()){
            return false;
        }
        return true;
    }
    void add_edge(Edge<K, V> e){
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
};