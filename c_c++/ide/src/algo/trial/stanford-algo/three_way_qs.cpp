#include <iostream>
#include <vector>
#include <utility>
#include <cassert>

using std::endl;
using std::vector;
using std::cout;
using std::swap;
using std::cin;

void three_way_qs(int a[], unsigned int l, unsigned int h){
    if (l >= h){
        return;
    } else {
        //unsigned int i = partition(a, l, h);
        assert(i >= l && i < h);
        three_way_qs(a, l, i);
        three_way_qs(a, i+1, h);}
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

    three_way_qs(a, 0, v.size());
    for (unsigned int i = 0; i < v.size(); ++i) {
        cout << a[i] << " ";
    }

    cout << endl;
    cout << count << endl;
	return 0;
}
