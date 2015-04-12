#include <iostream>
#include <algorithm>
//#include <iterator>
#include "utility/file_reader.cpp"

//using std::back_inserter;
using std::cout;
using std::endl;
using std::copy;

template <class T>
long count_inversion_recur(vector<T>& v, int b, int e){
    if (b == e-1) {
        return 0;
    } else {
        int mid = (b+e)/2;
        long sum1 = count_inversion_recur(v, b, mid);
        long sum2 = count_inversion_recur(v, mid, e);
        //merge and count inversion
        vector<T> left(v.begin()+b, v.begin()+mid);
        vector<T> right(v.begin()+mid, v.begin()+e);
        long sum3 = sum1+sum2;
        int i = 0, j = 0;
        for (; i < mid-b && j < e-mid;) {
            if (left[i] > right[j]){
                sum3 += (mid-b-i);
                v[b+i+j] = right[j];
                ++j;
            } else {
                v[b+i+j] = left[i];
                ++i;
            }
        }
        copy(left.begin()+i, left.end(), v.begin()+b+i+j);
        copy(right.begin()+j, right.end(), v.begin()+b+i+j);
        return sum3;
    }

}

template <class T>
long count_inversion(vector<T> t){
    return count_inversion_recur(t, 0, t.size());
}
int main(int argc, char *argv[])
{
    if (argc == 1){
        cout << "Usage: count_inversion file_of_data";
    }
    File_reader r(argv[1]);
    vector<int> v;
    int i;
    while (r.next_int(i)){
        v.push_back(i);
    }
    cout << count_inversion(v) << endl;
    return 0;
}
