#include <algorithm>
#include <string>
#include <vector>
#include <fstream>
#include <iostream>
#include <cctype>
#include <iterator>
#include <map>

using std::cout;
using std::endl;

using std::count;
using std::sort;
using std::unique;
using std::find_if;
using std::find;
using std::copy;

using std::ifstream;
using std::ofstream;

using std::string;
using std::vector;
using std::map;

class A{
public:
    string w;
    int c;
    A (string s, int i):w(s), c (i){}

    bool operator < (const A& a) const {// why the const is a must
        if (c < a.c) {
            return true;
        } else if (c > a.c){
            return false;
        } else {
            int cmp = w.compare(a.w);
            if (cmp < 0) {
                return true;
            } else {
                return false;
            }
        }
    }
};

bool space(char c){
    return isspace(c);
}

bool nspace(char c){
    return !isspace(c);
}

bool not_legal(char c){
    return !(isalpha(c) || isspace(c) || c == '-' || c == '+' || c == '*');
}

template <class Out>
void split(const string& s, Out o){
    typedef string::const_iterator sit;
    sit b = s.begin();
    sit e = s.end();

    while (b != e) {
        sit word = find_if(b, e, nspace);
        sit word_end = find_if(word, e, space);
        sit nword = find_if(word_end, e, nspace);
        if (word_end != e){
            string temp = string(word, nword);
            if (find_if(temp.begin(), temp.end(), not_legal) == temp.end()){
                *o++ = temp;
            }
        }
        b =  word_end;
    }
}

bool in_aim(const char c, const string& s){
    return (find( s.begin(), s.end(), c) != s.end());
}

void operate (string& s){
    string::size_type i = 0;
    if ( ( i = s.find('*')) != string::npos){
        s.replace(i, 1, 1, 's');
    } else if ( (i = s.find('+')) != string::npos){
        s.erase(i,1);
    } else if ( (i = s.find('-')) != string::npos){
        string aim = s.substr(i+1);
        s.erase(i);
        for (unsigned int i = 0; i < s.size(); ++i){
            if ( in_aim(s[i], aim) ){
                s.erase(i, 1);
            }
        }
    }
    std::transform(s.begin(), s.end(), s.begin(), ::tolower);
}

int main(int argc, char *argv[])
{
    ifstream data("test10.txt");
    ofstream re1("re1");
    ofstream re2("re2");
    if (data && re1 && re2) {
        string temp;
        map<string, int> word_count;
        while( getline(data, temp) ){
            vector<string> word_list;
            split(temp, back_inserter(word_list));
            std::for_each(word_list.begin(), word_list.end(), operate);
            for (unsigned int i = 0; i < word_list.size() ; ++i){
                re1 << word_list[i];
                ++word_count[word_list[i]];
            }
            re1 << endl;
        }
        vector<A> as;
        for(map<string, int>::const_iterator it = word_count.begin(); it != word_count.end(); ++it){
            as.push_back(A(it->first, it->second));
        }
        sort(as.begin(), as.end());
        for(unsigned int i = 0; i < as.size(); ++i){
            re2 << as[i].c << " " << as[i].w << endl;
        }
    } else {
        cout << "Fail to open the file" << endl;
    }

    return 0;
}
