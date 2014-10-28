#include <algorithm>
#include <iostream>
#include <map>

using namespace std;

int main(){
    map<string, int> strCounter;

    string s;
    while(cin >> s){
        ++strCounter[s];
    }

    int i = 1;
    for(; !strCounter.empty(); ++i){
        cout << i << ":" ;
        map<string, int>::const_iterator b = strCounter.begin();
        for(; b != strCounter.end(); ++b){
            if(b->second==i){
                cout << b->first;
                strCounter.erase;
            }
        }
    }
}