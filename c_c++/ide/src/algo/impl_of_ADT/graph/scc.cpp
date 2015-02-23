#include <iostream>
#include <stack>
#include <exception>
#include <algorithm>
#include <set>
#include <string>
#include <vector>
#include <iterator>
#include "digraph.hpp"
#include "utility/console_reader.cpp"

using std::cout;
using std::endl;
using std::vector;
using std::exception;
using std::stack;
using std::set;
using std::string;
using std::back_inserter;

#define GAP 1

/*
  args: {:}
  return value:
  Usage:
  1. enter the two side of edge
  2. direction: first <--- second
*/
template <class K, class V>
void Digraph<K, V>::set_edge_istream(Reader& r) {
    int t, h;
    while (r.next_int(t)) {
        if (!r.next_int(h)) {
            throw exception();
        }
        // reverse the tail and head at first
        this->add_edge(h-GAP, t-GAP);
    }
}

template <class K, class V>
void Digraph<K, V>::count_finish(vector< Vertex<K, V>*>& v, vector<K>& f) {
    stack<int> stack;
    for (unsigned int i = 0; i < v.size(); ++i) {
        if (v[i] == NULL) {
            assert(!stack.empty());
            auto tmp = stack.top();
            f.push_back(tmp);
            stack.pop();
        } else {
            stack.push(v[i]->key());
        }
    }
    assert(stack.empty());
}

/*
template <class K, class V>
void Digraph<K, V>::count_finish(vector< Vertex<K, V>*>& v, vector<K>& f) {
    stack<int> stack;
    bool last_not_null = true;
    for (unsigned int i = 0; i < v.size(); ++i) {
        if (v[i] == NULL) {
            assert(!stack.empty());
            if (last_not_null) {
                stack.pop();
            } else {
                auto tmp = stack.top();
                f.push_back(tmp);
                stack.pop();
            }
            last_not_null = false;
        } else {
            last_not_null = true;
            stack.push(v[i]->key());
        }
    }
    assert(stack.empty());
}
*/

template <class K, class V>
void Digraph<K, V>::analyse_part(vector< Vertex<K, V>*>& v) {
    assert(v.size() >= 2);
    K fa = v[0]->key();
    for (unsigned int i = 1; i < v.size(); ++i) {
        if (v[i] != NULL) {
            connect.unio(fa, v[i]->key());
        }
    }
}

template <class K, class V>
void Digraph<K, V>::find_finish(vector<K>& finish) {
    //reset
    search_set.clear();
    
    for (int i = vertices.size() - 1; i >= 0; i--) {
        if (search_set.count(i) == 0) {
            vector< Vertex<K, V>*> tra;
            dfs_loop(vertices[i], back_inserter(tra));
            count_finish(tra, finish);
        }
    }
    //    cout << finish.size() << " " << vertices.size() << endl;
    assert(finish.size() == vertices.size());
}

template <class K, class V>
void Digraph<K, V>::reverse_edges() {
    for (unsigned int i = 0; i < edges.size(); ++i) {
        edges[i]->reverse();
    }
}

template <class K, class V>
void Digraph<K, V>::finish_loop(vector<K>& finish){
    reverse_edges();
    search_set.clear();
    if (connect.size_of() != vertices.size()) {
        throw exception();
    }
    connect.reset();

    for (int i = finish.size()-1; i >= 0; i--) {
        if (search_set.count(finish[i]) == 0) {
            vector< Vertex<K, V>*> tra;
            dfs_loop(vertices[finish[i]], back_inserter(tra));
            analyse_part(tra);
        }
    }
}

/*
template <class K, class V>
void Digraph<K, V>::finish_loop(vector<K>& finish, vector<int> size){
    search_set.clear();
    vector< Vertex<K, V>*> tra;
    for (int i = finish.size()-1; i >= 0; i--) {
        if (search_set.count(i) == 0) {
            dfs(vertices[i], back_inserter(tra));
            assert(tra.size()%2 == 0);
            size.push_back(tra.size()/2);
        }
    }
}
*/

template <class K, class V>
template <class O>
void Digraph<K, V>::scc_size(O out) {
    vector<K> finish;
    find_finish(finish);
    vector<int> temp;
    set<K> set;
    finish_loop(finish);
    for (unsigned int i = 0; i < vertices.size(); ++i) {
        K k = vertices[i]->key();
        int fa = connect.father(k);
        if (set.count(fa) == 0) {
            set.insert(fa);
            temp.push_back(connect.ith_size(fa));
        }
    }
    std::sort(temp.begin(), temp.end());
    std::copy(temp.begin(), temp.end(), out);
}

int main()
{
    unsigned int size = 11;
    //    std::cin >> size;
    Digraph<int, int> g(size);
    Console_reader r;
    for (unsigned int i = 0; i < size; ++i) {
        g.add_vertex(i, i);
    }
    g.set_edge_istream(r);
    vector<int> res;
    g.scc_size(back_inserter(res));
    unsigned int sum = 0;
    for (auto& i: res) {
        cout << i << " ";
        sum += i;
    }
    //    cout << sum << endl;
    assert(sum == g.vertices.size());
    return 0;
}
