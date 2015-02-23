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
    vector< Vertex<int, string>* > vv;
    for (unsigned int i = 0; i < size; ++i) {
        vv.push_back( new Vertex<int, string>(i, std::to_string(i)));
    }
    Digraph<int, string>* g = new Digraph<int, string>();
    g->add_vertices(vv);
    
    Edge<int, string>* e01 = new Edge<int, string>(vv[0], vv[1]);
    Edge<int, string>* e02 = new Edge<int, string>(vv[0], vv[2]);
    Edge<int, string>* e12 = new Edge<int, string>(vv[1], vv[2]);
    Edge<int, string>* e13 = new Edge<int, string>(vv[1], vv[3]);
    Edge<int, string>* e23 = new Edge<int, string>(vv[2], vv[3]);
    Edge<int, string>* e20 = new Edge<int, string>(vv[2], vv[0]);
    Edge<int, string>* e31 = new Edge<int, string>(vv[3], vv[1]);

    vector< Edge<int, string>* > e;
    e.push_back(e01);
    e.push_back(e02);
    e.push_back(e12);
    e.push_back(e13);
    e.push_back(e23);
    e.push_back(e20);
    e.push_back(e31);
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
    cout << s.top() << "<-";
    s.pop();
    while (!s.empty() && s.top() != o){
        cout << s.top() << "<-";
        s.pop();
    }
    cout << o << endl;
}

int main()
{
    

    Digraph<int, string>* g = set_graph();
    
    // traverse
    vector< Vertex<int, string>* > vt;
    g->dfs(back_inserter(vt), 0);
    /*
    for (unsigned int i = 0; i < vt.size(); ++i) {
        if (vt[i] != NULL) {
            cout << vt[i]->key() << endl;
        } else {
            cout << "null" << endl;
        }
    }
    */
    // print
    stack<int> stack;
    set<int> set;
    for (unsigned int i = 0; i < vt.size(); ++i) {
        if (vt[i] != NULL) {
            //cout << vt[i]->key() << endl;
            if (set.find(vt[i]->key()) != set.end()){
                set.insert(vt[i]->key());
                stack.push(vt[i]->key());
                print_stack(stack);
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
