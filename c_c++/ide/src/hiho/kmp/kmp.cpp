#include <string>
#include "utility/console_reader.cpp"
#include <iostream>
#include <vector>
//#include

using std::string;
using std::endl;
using std::vector;
using std::cout;

class Kmp
{
    string pattern;
    vector<int> pi;

    void compute_prefix(string&);
public:
    Kmp(string s){
        pattern = s;
        //        cout << pattern << endl;
        compute_prefix(pattern);
    }
    virtual ~Kmp(){}

    int match(string);
};

int Kmp::match(string target){
    //    cout << target << endl;
    int count = 0;
    int j = 0;
    for (unsigned int i = 0; i < target.size(); ++i) {
        if (j > 0 && target[i] != pattern[j]) {
            j = pi[j-1];
        }

        if (target[i] == pattern[j]) {
            j++;
        } 
        if (j == pattern.size()) {
            j = pi[j-1];
            count++;
        }
    }
    return count;
}

void Kmp::compute_prefix(string& m){
    pi.push_back(0);

    int k = 0;
    for (unsigned int i = 1; i < m.size(); ++i) {
        if (m[i] == m[k]) {
            k++;
        } else {
            k = 0;
        }
        pi.push_back(k);
    }
    /*
    for (unsigned int i = 0; i < pattern.size(); ++i) {
        cout << pi[i] << " ";
    }
    cout << std::endl;
    */
}

int main(int argc, char *argv[])
{
    Console_reader r;
    r.readlines();
    vector<string> lines = r.lines();
    int times = atoi(lines[0].c_str());
    for (unsigned int i = 1; i < 2*times+1; i+=2) {
        Kmp k(lines[i]);
        cout << k.match(lines[i+1]) << endl;
    }
    return 0;
}
