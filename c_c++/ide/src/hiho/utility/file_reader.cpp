#ifndef _UTILITY_FILE_RAEDER_
#define _UTILITY_FILE_RAEDER_



#include <iostream>
#include <exception>
#include <fstream>
#include <string>
#include <vector>
#include "reader.cpp"

using std::vector;
using std::getline;
using std::ifstream;
using std::string;
using std::istream;
using std::ios;

class File_reader: Reader {
    vector<string> mlines;
    vector<string> mwords;
    string path;
    ifstream in;

public:
    File_reader(string p):path(p), in(){
        in.open(path);
        if(!in.is_open()){
            throw std::exception();
        }
    }

    istream& next_word(string& str) {
        in >> str;
        mwords.push_back(str);
        return in;
    }
    istream& next_int(int& i){
        string s;
        in >> s;
        if (in){
            i =  std::stoi(s);
        }
        return in;
    }
    istream& next_double(double& d){
        string s;
        in >> s;
        if (in){
            d = std::stod(s);
        }
        return in;
    }
    istream& readwords() {
        string s;
        while (next_word(s)){
        }
        return in;
    }

    istream& next_line(string& str) {
        getline(in, str);
        mlines.push_back(str);
        return in;
    }
    istream& readlines() {
        string s;
        while (next_line(s)) {
        }
        return in;
    }
    vector<string> lines() const{
        return mlines;
    }
    vector<string> words() const{
        return mwords;
    }

    void reset () {
        in.clear();
        in.seekg(0, ios::beg);
    }
    void clear_words(){
        mwords.clear();
    }
    void clear_lines(){
        mlines.clear();
    }
};

#endif /* _UTILITY_FILE_RAEDER_ */