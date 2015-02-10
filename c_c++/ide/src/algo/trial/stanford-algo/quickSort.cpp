#include <iostream>
#include <vector>
#include <utility>
#include <cassert>

using std::endl;
using std::vector;
using std::cout;
using std::swap;
using std::cin;


/*
  args: l-- lower bound, h-- high bound
  return value: the index of the pivot element in array [l, h)
  Usage:
*/

unsigned int pivot1(int a[], unsigned int l, unsigned int h){
    return l;
}
unsigned int pivot2(int a[], unsigned int l, unsigned int h){
    return h-1;
}
unsigned int pivot3(int a[], unsigned int l, unsigned int h){
    int middle = (l+h-1)/2;
    if ( (a[h-1] < a[middle] && a[middle] < a[l])
        || (a[middle] < a[h-1] && a[l] < a[middle])) {
        return middle;
    } else if ((a[h-1] < a[middle] && a[h-1] > a[l])
        || (a[middle] < a[h-1] && a[l] > a[h-1])) {
        return h-1;
    } else {
        return l;
    }

}

unsigned int partition(int a[], unsigned int l, unsigned int h){
    unsigned int p = pivot3(a, l, h);
    swap(a[p], a[l]);

    unsigned int j = l;
    for (unsigned int i = l+1; i < h; ++i) {
        if (a[i] < a[l]) {
            j++;
            swap(a[j], a[i]);
        }
    }
    swap(a[j], a[l]);
    return j;
}

long count = 0;

void quickSort(int a[], unsigned int l, unsigned int h){
    if (l >= h){
        return;
    } else {
        count += (h-l-1);
        unsigned int i = partition(a, l, h);
        assert(i >= l && i < h);
        quickSort(a, l, i);
        quickSort(a, i+1, h);}
}

int main(int argc, char *argv[]){
    int x = 0;
    vector<int> v;
    while (cin >> x){
        v.push_back(x);
    }
    int *a = new int[v.size()];
    for (unsigned int i = 0; i < v.size(); ++i) {
        a[i] = v[i];
    }

    quickSort(a, 0, v.size());
    for (unsigned int i = 0; i < v.size(); ++i) {
        cout << a[i] << " ";
    }

    cout << endl;
    cout << count << endl;
	return 0;
}
