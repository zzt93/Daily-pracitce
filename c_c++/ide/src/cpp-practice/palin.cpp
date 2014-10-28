#include <algorithm>
#include <string>
#include <iostream>

using namespace std;

bool is_pali(const string& s){
    return equal(s.begin(), s.end(), s.rbegin());
}

int main (){
    string test;
    cin >> test;
    cout << is_pali(test);
}

