#include <iostream>
#include <sstream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <string>

using namespace std;

void swap(int a[], int delim, int size){
    int i = 0;
    int n = size - delim;
    int m = delim;
    for(; i < n; ++i){
        m = delim;
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

bool space(char c){
    return isspace(c);
}
bool not_space(char c){
    return !isspace(c);
}
template <class Out>
void split(string s, Out e){
    string::iterator sit, sb = s.begin(), se = s.end();
    while(sb != se){
        sb = find_if(sb, se, not_space);//find the first non-space char, ie skip the space
        if(sb == se){
            break;
        }
        sit = find_if(sb, se, space);
        *e++ = string(sb, sit);
        sb = sit;
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
            //must use the adapter to change it even it is random-access iterator
            //for they are not the relationship of super and sub
            split(line, back_inserter(nums));
            for (iter=nums.begin(); iter!=nums.end(); ++iter) {
                is.str(*iter);
                is.clear();
                is >> a[i++];
            }
            nums.clear();
        }
        data.close();
        //get the delimeter
        cout << "enter the delimiter (it must not smaller than 0 and smaller than " << i << ")\n";
        cout << "[0, delimeter) [delimeter, i)\n";
        int m;
        cin >> m;
        if (m > i || m < 0) {
            cerr << "delimiter is too large" << endl;
            exit(0);
        } else {
            swap(a, m, i);
        }
        //write back to file
        ofstream out;
        out.open("data.txt", ios::out | ios::app);
        int index = 0;
        out << "\n";
        while(index < i-1){
            out << a[index++] << ", ";
        }
        out << a[i-1];
    } else {
        cout << "can not open file";
    }
    return 0;
}

