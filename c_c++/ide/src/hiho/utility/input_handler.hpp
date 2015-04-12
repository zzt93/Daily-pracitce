#ifndef INPUT_HANDLER_H
#define INPUT_HANDLER_H

#include <iostream>
#include <vector>
#include <string>
#include <sstream>

using std::getline;
using std::vector;
using std::string;
using std::stringstream;

class Input_handler {
public:
    Input_handler(){}
    
    //split
    vector<string> &split(const string &s, char delim, vector<string> &elems) {
        stringstream ss(s);
        string item;
        while (getline(ss, item, delim)) {
            elems.push_back(item);
        }
        return elems;
    }

    vector<string> split(const string &s, char delim) {
        vector<string> elems;
        split(s, delim, elems);
        return elems;
    }

    vector<string> split(const string &s) {
        stringstream ss(s);
        string item;
        vector<string> elems;
        while (ss >> item) {
            elems.push_back(item);
        }
        return elems;
    }
    template <class O>
    void split(const string& s, O out) {
        vector<string> tmp = split(s);
        for (string& s: tmp) {
            *out++ = s;
        }
    }
    template <class O>
    void split(const string& s, char delim, O out) {
        vector<string> tmp = split(s, delim);
        for (string& s: tmp) {
            *out++ = s;
        }
    }
    
    //to ints
    vector<int> to_ints(vector<string> elems) {
        vector<int> ints;
        for (string& s: elems) {
            int i = std::stoi(s);
            ints.push_back(i);
        }
        return ints;
    }
    int to_int(string& s) {
        return std::stoi(s);
    }
    vector<double> to_doubles(vector<string> elems) {
        vector<double> doubles;
        for (string& s: elems) {
            double i = std::stoi(s);
            doubles.push_back(i);
        }
        return doubles;
    }

};

#endif /* INPUT_HANDLER_H */