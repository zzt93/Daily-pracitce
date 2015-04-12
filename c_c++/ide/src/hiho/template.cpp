#include <iostream>
#include <exception>
#include <stack>
#include <string>
#include <iterator>
#include <vector>
#include <map>
#include <cstdlib>

using std::vector;
using std::exception;
using std::string;
using std::stack;
using std::map;
using std::back_inserter;

using std::cout;
using std::endl;
//using std::stoi;

using std::getline;
using std::cin;
using std::istream;

#ifndef _my_reader_
#define _my_reader_


class Reader {

public:
    Reader(){}

    virtual istream& next_word(string&) = 0;
    virtual istream& readwords() = 0;
    virtual istream& next_line(string&) = 0;
    virtual istream& readlines() = 0;
    virtual vector<string> lines() const = 0;
    virtual vector<string> words() const = 0;

};

#endif

class Console_reader: Reader {
    vector<string> mlines;
    vector<string> mwords;

public:
    Console_reader(){}

    istream& next_word(string& s) {
        cin >> s;
        mwords.push_back(s);
        return cin;
    }
    istream& readwords() {
        string s;
        while (next_word(s)){
        }
        return cin;
    }
    istream& next_int(int& i){
        string s;
        cin >> s;
        if (cin){
            //i =  std::stoi(s);
	    i = atoi( s.c_str() );
        }
        return cin;
    }
    
 
    istream& next_line(string& s) {
        getline(cin, s);
        mlines.push_back(s);
        return cin;
    }
    istream& readlines() {
        string s;
        while (next_line(s)) {
        }
        return cin;
    }
    vector<string> lines() const{
        return mlines;
    }
    vector<string> words() const{
        return mwords;
    }

    void reset () {
        cin.clear();
        //    cin.ignore();
    }
    void clear_words(){
        mwords.clear();
    }
    void clear_lines(){
        mlines.clear();
    }
};



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


