#include <iostream>
#include <fstream>
#include <string>
#include <map>
#include <set>
#include <algorithm>
#include <vector>

using namespace std;
typedef string::const_iterator cit;

bool not_url(char c){
    static const string urlChar= "~;/?:@=&$-_.+!*'(),";
    return !(isalnum(c) ||
        (find(urlChar.begin(), urlChar.end(), c) != urlChar.end()));
}
cit url_helper(cit b, cit m, bool isAdd){
    if(isAdd){
        return find_if(m, b, not_url);
    } else {
        while(m-- != b){
            if(!isalpha(*m)){
                return ++m;
            }
        }
        return b;
    }
}
vector<string> get_url(const string& line){
    vector<string> res;
    const string symbol = "://";
    string::const_iterator midS;
    cit b = line.begin(), e = line.end();
    while((midS = search(b, e, symbol.begin(), symbol.end())) != e){
        cit be =  url_helper(b, midS, false);
        cit end = url_helper(e, midS, true);
        b = end;
        if(be != midS && (end-1) != (midS+1)){//make sure at least one character after and before ://
            res.push_back(string(be, end));
        }
    }
}

int main(int argc, char *argv[]){
    map< string, set<int> > counterOfU;
    if (argc == 1){
        std::cout << "Usage: input './a.out file_name' "
                  << "and it will read the file and print the number of URL" << std::endl;
    } else {
        ifstream URLfile(argv[1]);
        string line;
        int lineNum = 0;
        vector<string> urls;
        vector<string>::iterator url;
        if(URLfile.is_open()){
            while(getline(URLfile, line)){
                urls = get_url(line);
                for (url = urls.begin(); url != urls.end(); url++) {
                    counterOfU[*url].insert(lineNum);
                }
            }
            URLfile.close();
        } else {
            cerr << "can't open file";
        }
        map<string, set<int> >::const_iterator iter;
        set<int> temp;
        set<int>::const_iterator sit;
        for (iter=counterOfU.begin(); iter!=counterOfU.end(); ++iter) {
            cout << iter->first << "occurs at:";
            temp = iter->second;
            for (sit=temp.begin(); sit!=temp.end(); ++sit) {
                std::cout << *sit << std::endl;
            }
        }
    }
}

