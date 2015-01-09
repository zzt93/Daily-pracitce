#include <iostream>
#include <algorithm>
#include <fstream>
#include <cctype>
#include <iterator>

using std::cout;
using std::endl;
using std::ifstream;
using std::find;
using std::find_if;
using std::string;
using std::cin;

bool space(char c){
    return isspace(c);
}

bool not_space(char c){
    return !isspace(c);
}

template <class Out>
void split(const string& s, Out os){

    typedef string::const_iterator siter;
    siter b = s.begin();
    siter e = s.end();

    while(b != e){
        siter word = find_if(b, e, not_space);
        siter word_end = find_if(word, e, space);
        if (word != e){
            *os++ =  string(word, word_end);
        }
        b = word_end;
    }
}

int main(int argc, char *argv[])
{
    string s;
    while (getline(cin, s)){
        split(s, std::ostream_iterator<string>(cout, "\n"));
    }
    return 0;
}
