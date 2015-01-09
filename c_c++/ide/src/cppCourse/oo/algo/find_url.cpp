#include <iostream>
#include <algorithm>
#include <iterator>
#include <string>
#include <cctype>

using std::ostream_iterator;
using std::cout;
using std::cin;
using std::endl;
using std::string;
using std::search;

typedef string::const_iterator sit;

bool not_url(char c){
    static const string url_str = "~;/?:@=&$-_.+!*'(),";
    return !(isalnum(c) ||
        find(url_str.begin(), url_str.end(), c) != url_str.end());
}

sit url_begin(sit be, sit end){
    static const string symbol = "://";
    // sym is for marking the place of "://"
    sit sym = be;
    while ( (sym = search(sym, end, symbol.begin(), symbol.end())) != end ){
        //make sure the symbol is not the only string in this line
        if (sym == be || sym == end+symbol.size()){
            continue;
        }
        // be_u is for find the begin of the protocol
        sit be_u = sym;
        while(be_u >= be && isalpha(be_u[-1])){
            be_u --;
        }
        // make sure the at least one char is before "://" and after it
        if (be_u != sym && !not_url(sym[symbol.size()])){
            return be_u;
        }
        sym += symbol.size();
    }
    return end;
}


sit url_end(sit be, sit end){
    return find_if(be, end, not_url);
}

template <class T>
void find_urls(const string& s, T os){

    sit b = s.begin();
    sit e = s.end();

    while (b != e){
        b = url_begin(b, e);
        sit url_e = url_end(b, e);
        if (b != e){
            *os++ = string(b, url_e);
        }
        b = url_e;
    }
}
int main()
{
    string s;
    while (getline(cin, s)){
        find_urls(s, std::ostream_iterator<string>(cout, "\n"));
    }
    return 0;
}
