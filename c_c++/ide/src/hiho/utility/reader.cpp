#ifndef _my_reader_
#define _my_reader_

#include <iostream>
#include <vector>

using std::string;
using std::istream;
using std::vector;

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
