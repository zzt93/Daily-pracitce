#include <iostream>
#include <iterator>
#include <string>
#include <vector>
#include "utility/console_reader.cpp"
#include "trie_tree.cpp"

using std::cout;
using std::endl;
using std::back_inserter;
using std::vector;
using std::stoi;

int main()
{
    Console_reader r;
    r.readlines();
    vector<string> lines = r.lines();
    unsigned int size = stoi(lines[0]);
    Trie_tree tree;
    for (unsigned int i = 1; i <= size; ++i) {
        tree.add(lines[i]);
    }
    /*
    for (unsigned int i = 0; i < res.size();++i) {
        if (res[i] == NULL){
            cout << "NULL" << " ";
        } else {
            cout << res[i]->getChar() << " ";
        }
    }
    cout << endl;
    */
    int times = stoi(lines[size+1]);
    for (unsigned int i = 2+size; i <= size+times+1; ++i) {
        cout << lines[i] << " " << tree.search(lines[i]) << endl;
        vector<string> res;
        tree.begin_with(back_inserter(res), lines[i]);
        for (unsigned int i = 0; i < res.size(); ++i) {
            cout << res[i] << " ; ";
        }
        cout << endl;
    }
    return 0;
}
