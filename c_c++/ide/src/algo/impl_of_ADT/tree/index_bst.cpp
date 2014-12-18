#include <iostream>
#include <fstream>
#include "index_bst.hpp"

using std::endl;
using std::cout;

int main(int argc, char *argv[])
{
    Index_tree_node<int> a(1);
    //    cout << a.data() << endl;
    int ar[] = {1, 2, 3, 4, 5, 6, 7, 8};
    Index_tree<int> t(ar, sizeof(ar)/sizeof ar[0]);
    //    cout << t;
    Index_tree<int> t2(t);
    //cout << t2;
    Index_tree<int> t3;
    t3 = t2;
    cout << t3;
    return 0;
}

