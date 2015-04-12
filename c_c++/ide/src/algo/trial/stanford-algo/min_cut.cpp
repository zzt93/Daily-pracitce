#include <iostream>
#include <cmath>
#include <iterator>
#include <algorithm>
#include <cassert>
#include <random>
#include "utility/console_reader.cpp"
#include "utility/input_handler.hpp"
#include "union/improved_union.cpp"

using std::endl;
using std::back_inserter;
using std::cout;

static const int GAP_INDEX = 1;
static const int HEAD = 1;
static Union* u;

vector< vector<int> > to_int(vector< vector<string> >& ve){
    vector< vector<int> > res;
    for (unsigned int i = 0; i < ve.size(); ++i) {
        vector<int> t;
        for(string s: ve[i]){
            t.push_back(std::stoi(s));
        }
        res.push_back(t);
    }
    return res;
}

void contract(int i, int j, vector< vector<int> >& v){
    //cout << "i: " << i << " j: " << j << endl;
    assert( i < v.size() && i > 0);
    assert( j < v[i].size() && j > 0);
    //remove the shared edge
    int v2 = u->father(v[i][j]);
    u->unio(i, v2);
    vector<int> temp;
    //copy to the i-th vertex except the edge of i--v2 and other merged vertices
    //copy to the v2-th column
    for (auto it=v[i].begin()+HEAD; it!=v[i].end(); ++it) {
        if (!u->connected(i, *it)) {
            temp.push_back(*it);
        }
    }
    for (auto it=v[v2].begin()+HEAD; it!=v[v2].end(); ++it) {
        if (!u->connected(i, *it)) {
            temp.push_back(*it);
        }
    }
    v[v2].erase(v[v2].begin()+HEAD, v[v2].end());
    v[i].erase(v[i].begin()+HEAD, v[i].end());
    //copy the new edge to father
    int fa = u->father(i);
    assert(fa < v.size() && fa > 0);
    for (unsigned int i = 0; i < temp.size(); i++) {
        v[fa].push_back(temp[i]);
    }

}

int simulate(vector< vector<int> > v){
    std::random_device rd;
    std::mt19937 mt(rd());
    unsigned int size = v.size();
    for (unsigned int i = 0; i < size-2-HEAD; i++) {
        assert(1 <= v.size()-1);
        std::uniform_int_distribution<int> dist(1, v.size()-1);
        int ran_in = dist(mt);
        int in = u->father(ran_in);
        assert(HEAD <= v[in].size()-1);
        std::uniform_int_distribution<int> dist2(HEAD, v[in].size()-1);
        int j = dist2(mt);
        contract(in, j, v);
    }
    assert(u->parts() == 2+HEAD);
    int fa = u->father(1);
    return v[fa].size()-HEAD;
}

int min_cut(vector< vector<string> >& ve){
    unsigned int min = -1;
    unsigned int ve_size = ve.size();
    vector< vector<int> > ints = to_int(ve);
    ints.insert(ints.begin(), vector<int>(0));
    for (unsigned int i = 0; i < ve_size*ve_size*log(ve_size); i++) {
        u = new Union(ve_size+1);
        int temp = simulate(ints);
        cout << temp << "temp" << endl;
        if (min > temp) {
            min = temp;
        }
        delete u;
    }
    return min;
}

int main(int argc, char *argv[])
{
    Console_reader r;
    Input_handler in;
    vector<vector<string> > v;
    string s;
    while (r.next_line(s)){
        v.push_back(in.split(s));
    }
    cout << min_cut(v) << endl;
    return 0;
}