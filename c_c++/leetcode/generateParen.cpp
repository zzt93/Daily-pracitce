#include <iostream>
#include <vector>

using std::endl;
using std::cout;

using namespace std;

void recur(vector<string>& res, string& s, int i, int n_l, int n_r) {
    if(n_l == 0 && n_r == 0) {
        res.push_back(s);
        return;
    }
    if(n_l > n_r) {
        return;
    }
    if(n_l > 0) {
        s[i] = '(';
        recur(res, s, i + 1, n_l - 1, n_r);
    }
	if(n_r > 0) {
		s[i] = ')';
		recur(res, s, i + 1, n_l, n_r - 1);
	}
}

vector<string> generateParenthesis(int n) {
    vector<string> res;
    string s(2 * n, ' ');
    int i = 0;
    recur(res, s, i, n, n);
    //sort(res.begin(), res.end());
    return res;
}


int main(int argc, char *argv[]){
	generateParenthesis(4);
	return 0;
}

/**
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        recur(res, n);
        //sort(res.begin(), res.end());
        return res;
    }
    void recur(vector<string>& res, int n) {
        if(n == 1 || n == 0) {
            res.push_back(n == 0 ? "" : "()");
            return;
        }
        recur(res, n - 1);
        // the first in res is always symmetric one ()()()...
        vector<string> tmp(res.size() * 3 - 1);
        tmp[0] = res[0] + "()";
        tmp[1] = '(' + res[0] + ')';
        int j = 2;
        for(int i = 1; i < res.size(); i++) {
            tmp[j++] = res[i] + "()";
            tmp[j++] = "()" + res[i];
            tmp[j++] = '(' + res[i] + ')';
        }
        res.clear();
        res.insert(res.begin(), tmp.begin(), tmp.end());
    }
*/
