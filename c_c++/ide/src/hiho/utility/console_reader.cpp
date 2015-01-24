#include <iostream>
#include <string>
#include <vector>
#include "reader.cpp"

using std::vector;
using std::getline;
using std::cin;
using std::string;
using std::istream;

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
