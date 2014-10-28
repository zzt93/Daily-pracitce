#include <iostream>
#include <sstream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <string>

using namespace std;

void swap(int a[], int m, int n){
    int i = 0;
    int dm = m;
    for(; i < n; ++i){
        m = dm;
        while (m) {
            int temp = a[m+i] ;
            a[m+i] = a[m+i-1];
            a[m+i-1] = temp;
            --m;
        }
    }
}

void int_s(int c, char *s){
    int times = log10(c);
    if (times) {
        *s++ = c+'0';
    } else {
       int_s(c/10, s);
        *s++ = c%10;
    }

}

template <class Out>
void split(string s, Out b, Out e){
    string::iterator sit, sb = s.begin(), se = s.end();
    while(b != e){
        while(sb != se){
            sit = find(sb, se, ' ');
            *b++ = string(sb, sit);
            sb = ++sit;
        }
    }
}

int main()
{
    ifstream data("data.txt");
    if(data.is_open()){
        string line;
        int a[100] = {0};
        vector<string> nums;
        vector<string>::iterator iter;
        int i = 0;
        istringstream is;
        while (getline(data, line) && i < 100) {
            split(line, nums.begin(), nums.end());
            for (iter=nums.begin(); iter!=nums.end(); ++iter) {
                is.str(*iter);
                is >> a[i++];
            }
        }
        data.close();
        //get the delimeter
        cout << "enter the delimiter (it must smaller than )" << i;
        int m;
        cin >> m;
        if (m > i) {
            cerr << "delimiter is too large";
            exit(0);
        } else {
            swap(a, m, i-m);
        }
        //write back to file
        ofstream out;
        out.open("data.txt", ios::out | ios::app);
        int index = 0;
        while(index < i-1){
            out << a[index] << ", ";
        }
        out << a[i-1];
    } else {
        cout << "can not open file";
    }
    return 0;
}

