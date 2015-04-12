#include <iostream>
#include <cmath>

using std::cout;
using std::endl;

bool in_range(double pivot, int range, double now, int i){
    if (now+i <= pivot+range && now-i >= pivot-range) {
        return true;
    }
    return false;
}


/*
  args: pivot -- the middle of the string;
  args: next_p -- the reference of the last pivot, and will be the next pivot
  return value: true if change to symmetric position
  Usage:
*/
bool symmetric_or_next(double pivot, double& next_p){
    if (pivot < next_p){
        next_p = 2*pivot-next_p;
        return true;
    } else {
        next_p = 2*pivot-next_p + 0.5;
        return false;
    }
}

int intest_palin(string& s){
    int long_range = 0;
    int res = 0;
    int expect = s.size();

    double pivot = (s.size()-1)/2.0;
    double long_pivot = pivot;
    int lim = s.size()-pivot;
    while (expect > res) {
        int i = 1;
        for (; i < lim; ++i) {
            if (s[p-i] != s[p+i]) {
                break;
            }
        }
        int length = 2*i;
        if (pivot == floor(pivot)) {
            length = 2*i + 1;
        }

        if (res < length) {
            res = length;
            long_pivot = pivot;
            long_range = i;
        }

        //decide next pivot
        if (in_range(long_pivot, long_range, pivot, i)) {
            pivot = pivot + 0.5;
            //change expect
            //change limit
            if (pivot == floor(pivot)) {
                lim--;
            }
            expect--;
        } else {
            if (!symmetric_or_next((s.size()-1)/2.0, pivot)){
                //change expect
                //change limit
                if (pivot == floor(pivot)) {
                    lim--;
                }
                expect--;
            }
        }
    }

}

