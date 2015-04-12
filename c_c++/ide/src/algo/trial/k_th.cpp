#include <iostream>
#include <algorithm>
#include <vector>

using std::cout;
using std::endl;
using std::vector;
using std::partition;

template <class T>
T k_th_odd(vector<T> v, int k);

template <class T>
T k_th_even(vector<T> v, int k){
    if (v.size() == 2) {
        return (k==1)?v[0]:v[1];
    } else {
        long mid = v.size()/2;
        if (mid < k) {
            vector<T> large;
            for (unsigned int i = 0; i < mid; i++) {
                if (v[i] < v[i+mid]) {
                    large.push_back(v[i+mid]);
                } else {
                    large.push_back(v[i]);
                }
            }
            if (large.size()%2 == 0) {
                return k_th_even(large, k-mid);
            } else {
                return k_th_odd(large, k-mid);
            }
        } else {
            vector<T> small;
            for (unsigned int i = 0; i < mid; i++) {
                if (v[i] > v[i+mid]) {
                    small.push_back(v[i+mid]);
                } else {
                    small.push_back(v[i]);
                }
            }
            if (small.size()%2 == 0) {
                return k_th_even(small, k);
            } else {
                return k_th_odd(small, k);
            }

        }
    }
}

template <class T>
T k_th_odd(vector<T> v, int k){
    if (v.size() == 1) {
        return v[0];
    } else if (v.size() == 2*k-1){//mean k is the median of v
        
    } else {
        long mid = v.size()/2;
        if (mid < k) {
            vector<T> large;
            for (unsigned int i = 0; i < mid; i++) {
                if (v[i] < v[i+mid]) {
                    large.push_back(v[i+mid]);
                } else {
                    large.push_back(v[i]);
                }
            }
            //tackle the last element
            large.push_back(v[v.size()-1]);
            if (large.size()%2 == 0) {
                return k_th_even(large, k-mid);
            } else {
                return k_th_odd(large, k-mid);
            }
        } else {
            vector<T> small;
            for (unsigned int i = 0; i < mid; i++) {
                if (v[i] > v[i+mid]) {
                    small.push_back(v[i+mid]);
                } else {
                    small.push_back(v[i]);
                }
            }
            small.push_back(v[v.size()-1]);
            if (small.size()%2 == 0) {
                return k_th_even(small, k);
            } else {
                return k_th_odd(small, k);
            }
        }
    }
}



int main(int argc, char *argv[])
{
    
    return 0;
}
