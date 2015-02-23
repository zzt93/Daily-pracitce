#ifndef _UITILITY_CONSOLE_READER_
#define _UITILITY_CONSOLE_READER_



#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include "reader.cpp"

using std::vector;
using std::getline;
using std::cin;
using std::string;
using std::istream;

class Console_reader: public Reader {
    vector<string> mlines;
    vector<string> mwords;

public:
    Console_reader(){}

    
    /*
      args: {:}
      return value: the reference of istream
      Usage: use like the following example
      while (next_word(str)) {
         cout << s << " ";
      }
    */
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
            i =  std::stoi(s);
        }
        return cin;
    }
    istream& next_double(double& d){
        string s;
        cin >> s;
        if (cin){
            d = std::stod(s);
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
    
    std::vector<std::string> &split(const std::string &s, char delim, std::vector<std::string> &elems) {
        std::stringstream ss(s);
        std::string item;
        while (std::getline(ss, item, delim)) {
            elems.push_back(item);
        }
        return elems;
    }

    std::vector<std::string> split(const std::string &s, char delim) {
        std::vector<std::string> elems;
        split(s, delim, elems);
        return elems;
    }

    std::vector<std::string> split(const std::string &s) {
        std::stringstream ss(s);
        std::string item;
        vector<string> elems;
        while (ss >> item) {
            elems.push_back(item);
        }
        return elems;
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

#endif /* _UITILITY_CONSOLE_READER_ */