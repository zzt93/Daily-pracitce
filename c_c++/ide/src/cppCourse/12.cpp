#include <map>
#include <fstream>
#include <algorithm>
#include <vector>
#include <string>
#include <iostream>

using namespace std;
typedef vector<int>::const_iterator Vcit;
typedef string::const_iterator Scit;
typedef map<int, vector<int> >::iterator Mit;

bool isYear(char c){
}
bool isTem(char c){
    return false;
}
int main(int argc, char*argv[]){
    ifstream data("data.txt");
    map<int, vector<int> > mapT;
    if(data.is_open()){
        string line;
        Scit bYear, bTem;
        int year, tem;
        while (getline(data, line)) {
            bYear = find_if(line.begin(), line.end(), isYear);
            bTem = find_if(line.begin(), line.end(), isTem);
            const char *t = &*bYear;
            //year = atoi(t);
        }
        Mit iter;
        for (iter=mapT.begin(); iter!=mapT.end(); ++iter) {
            Vcit test = max_element(mapT[iter->first].begin(), mapT[iter->first].end());
            
        }
        data.close();
    } else {
        cout << "can't open the file";
    }
}

