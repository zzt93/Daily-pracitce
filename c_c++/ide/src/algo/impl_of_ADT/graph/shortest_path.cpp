#include <iostream>
#include <exception>
#include <algorithm>
#include <string>
#include <vector>
#include <climits>
#include "digraph.hpp"
#include "utility/console_reader.cpp"
#include "utility/input_handler.hpp"

using std::cout;
using std::endl;
using std::vector;
using std::exception;
using std::string;

#define GAP 1

/*
  args: {:}
  return value:
  Usage:
  1. enter the two side of edge
  2. direction: first ---> second
*/
template <class K, class V>
void Digraph<K, V>::set_edge_istream(Reader& r) {
    string s;
    Input_handler in;
    while (r.next_line(s)) {
        //ignore the first entry
        vector<string> v;
        v = in.split(s);
        int src = in.to_int(v[0]);
        assert(src <= vertices.size() && src > 0);
        for (unsigned int i = 1; i < v.size(); i++) {
            vector<int> ints;
            ints = in.to_ints(in.split(v[i], ','));
            this->add_edge(src-GAP, ints[0]-GAP, ints[1]);
        }
    }
}

template <class K, class V>
void Digraph<K, V>::update_len(Vertex<K, V>* v, vector<long>& len, long dis, bool* found) {
    assert(v != NULL);
    v->init_out();
    while (v->has_out()) {
        auto next = v->next_out();
        int st = next->another()->key();
        long vt = next->wei();
        if (!found[st] && len[st] > vt + dis) {
            len[st] = vt + dis;
        }
    }
}

template <class K, class V>
vector<long> Digraph<K, V>::shortest_path(int start) {
    if (start >= vertices.size() || start < 0) {
        throw exception();
    }

    vector<long> res;
    vector<long> cross;
    bool found_or[vertices.size()];
    for (unsigned int i = 0; i < vertices.size(); ++i) {
        if (i == start) {
            res.push_back(0);
            found_or[i] = true;
        } else {
            res.push_back(LONG_MAX);
            found_or[i] = false;
        }
        cross.push_back(LONG_MAX);
    }

    int found = 1;//the count of vertices that shortest path has found
    int last_found = start;
    int dis = 0;
    while (found != vertices.size()) {
        //update the res according to the last found vertex
        update_len(vertices[last_found], cross, dis, found_or);
        //find min
        auto min_ele = std::min_element(cross.begin(), cross.end());
        int min = min_ele - cross.begin();
        res[min] = *min_ele;
        cross[min] = LONG_MAX;
        //update auxiliary
        found_or[min] = true;
        dis = res[min];
        last_found = min;
        found++;
    }
    return res;
}

int main()
{
    unsigned int size = 200;
    //    std::cin >> size;
    Digraph<int, int> g(size);
    Console_reader r;
    for (unsigned int i = 0; i < size; ++i) {
        g.add_vertex(i, i);
    }
    g.set_edge_istream(r);
    vector<long> res;
    res = g.shortest_path(0);
    for (unsigned int i = 0; i < res.size(); ++i) {
        cout << i+1 << "-" << res[i] << "      ";
    }
    return 0;
}
