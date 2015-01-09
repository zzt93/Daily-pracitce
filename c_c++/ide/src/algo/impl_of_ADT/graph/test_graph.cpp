#include <iostream>
#include <set>
#include <string>
#include <vector>
#include <stack>
#include <iterator>
#include "digraph.hpp"

using std::cout;
using std::endl;
using std::vector;
using std::stack;
using std::set;
using std::string;
using std::back_inserter;

Digraph<int, string>* set_graph(){
    unsigned int size = 4;
    vector< Vertex<int, string> > v1;
    for (unsigned int i = 0; i < size; ++i) {
        v1.push_back(Vertex<int, string>(i, std::to_string(i)));
    }
    Digraph<int, string>* g = new Digraph<int, string>();
    g->add_vertices(v1);
    
    vector< Vertex<int, string> > v ;
    g->all_vertices(back_inserter(v));
    static Edge<int, string> e01(v[0], v[1]);
    static Edge<int, string> e02(v[0], v[2]);
    static Edge<int, string> e12(v[1], v[2]);
    static Edge<int, string> e13(v[1], v[3]);
    static Edge<int, string> e23(v[2], v[3]);
    vector< Edge<int, string> > e;
    e.push_back(e01);
    e.push_back(e02);
    e.push_back(e12);
    e.push_back(e13);
    e.push_back(e23);
    
    g->add_edges(e);
    return g;
}

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

template <class T>
void print_stack(stack<T> s){
    T o = s.top();
    cout << "circle: " << endl;
    cout << s.top() << endl;
    s.pop();
    while (!s.empty() && s.top() != o){
        cout << s.top() << endl;
        s.pop();
    }
    cout << o << endl;
}

int main()
{
    
    unsigned int size = 4;
    vector< Vertex<int, string> > v;
    for (unsigned int i = 0; i < size; ++i) {
        v.push_back(Vertex<int, string>(i, std::to_string(i)));
    }
    Digraph<int, string> g ;
    
    Edge<int, string> e01(v[0], v[1]);
    Edge<int, string> e02(v[0], v[2]);
    Edge<int, string> e12(v[1], v[2]);
    Edge<int, string> e31(v[3], v[1]);
    Edge<int, string> e23(v[2], v[3]);
    vector< Edge<int, string> > e;
    e.push_back(e01);
    e.push_back(e02);
    e.push_back(e12);
    e.push_back(e31);
    e.push_back(e23);
    
    g.add_edges(e);
    g.add_vertices(v);


    // traverse
    vector< Vertex<int, string>* > vt;
    g.dfs(back_inserter(vt), 0);
    for (unsigned int i = 0; i < vt.size(); ++i) {
        if (vt[i] != NULL) {
            cout << vt[i]->key() << endl;
        } else {
            //cout << "null" << endl;
        }
    }
    // print
    stack<int> stack;
    set<int> set;
    for (unsigned int i = 0; i < vt.size(); ++i) {
        if (vt[i] != NULL) {
            cout << vt[i]->key() << endl;
            if (set.find(vt[i]->key()) != set.end()){
                set.insert(vt[i]->key());
                stack.push(vt[i]->key());
                //print_stack(stack);
            } else {
                set.insert(vt[i]->key());
                stack.push(vt[i]->key());
            }
        } else {
            set.erase(stack.top());
            stack.pop();
        }
    }
    return 0;
}
