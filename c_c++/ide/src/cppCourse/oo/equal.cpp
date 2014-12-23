#include <iostream>     // std::cout
#include <algorithm>    // std::equal
#include <vector>       // std::vector

using std::string;
using std::cout;
using std::endl;
using std::vector;
using std::equal;

bool mypredicate (int i, int j) {
    return (i==j);
}

bool is_pa(const string & s){
    return equal(s.begin(), s.end(), s.rbegin());
}

int main () {
    int myints[] = {20,40,60,80,100};               //   myints: 20 40 60 80 100
    std::vector<int>myvector (myints,myints+5);     // myvector: 20 40 60 80 100

    // using default comparison:
    if ( std::equal (myvector.begin(), myvector.end(), myints) )
    std::cout << "The contents of both sequences are equal.\n";
    else
    std::cout << "The contents of both sequences differ.\n";

    myvector[3]=81;                                 // myvector: 20 40 60 81 100

    // using predicate comparison:
    if ( std::equal (myvector.begin(), myvector.end(), myints, mypredicate) )
    std::cout << "The contents of both sequences are equal.\n";
    else
    std::cout << "The contents of both sequences differ.\n";

    string s("abadsdaba");
    if (equal(s.begin(), s.end(), s.rbegin())) {
        cout << "is pra" << endl;
    }

    return 0;
}
