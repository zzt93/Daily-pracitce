#include <iostream>
#include <vector>
#include <utility>
#include <cassert>

using std::endl;
using std::vector;
using std::cout;
using std::swap;
using std::cin;

void swap(vector<int>& a, unsigned int i, unsigned int j) {
    int t = a[i];
    a[i] = a[j];
    a[j] = t;
}


/*
  args: {:}
  return value: two indices of equal range of a[i], means [a[res[0]], a[res[1]])
  are all equal pivot
  Usage:
*/
vector<unsigned int> partition(vector<int>& a, unsigned int i, unsigned int j) {
    assert(i + 1 <= j);
    int pivot = a[i];
    unsigned int equ = i;
    unsigned int lar = i+1;
    unsigned int unseen = i+1;
    while (unseen < j) {
        if (a[unseen] < pivot) {
            // you can speed up by swap the three elements at a time
            swap(a, unseen, equ);
            equ++;
            swap(a, unseen, lar);
            lar++;
        } else if (a[unseen] == pivot) {
            swap(a, unseen, lar);
            lar++;
        }
        unseen++;
    }
    vector<unsigned int> res;
    res.push_back(equ);
    res.push_back(lar);
    return res;
}
vector<unsigned int> partition2(vector<int>& a, unsigned int i, unsigned int j) {
    
}

void three_way_qs(vector<int>& a, unsigned int l, unsigned int h){
    cout << l << " " << h << endl;
    if (l >= h){
        return;
    } else {
        vector<unsigned int> i = partition(a, l, h);
        assert(i[0] >= l && i[1] <= h);
        three_way_qs(a, l, i[0]);
        three_way_qs(a, i[1], h);}
}

int main(int argc, char *argv[]){
    int x = 0;
    vector<int> v;
    while (cin >> x){
        v.push_back(x);
    }
    three_way_qs(v, 0, v.size());
    for (unsigned int i = 0; i < v.size(); ++i) {
        cout << v[i] << " ";
    }
    cout << endl;
	return 0;
}
