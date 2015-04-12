#include "utility/daliy.hpp"
#include "utility/console_reader.cpp"
#include "utility/my_algo.hpp"
#include <algorithm>

using std::sort;

long min_product(vector<long>& v1, vector<long>& v2) {
    sort(v1.begin(), v1.end());
    sort(v2.begin(), v2.end());
    unsigned int last = v1.size() - 1;
    long sum = 0;
    for (unsigned int i = 0; i < v1.size(); ++i) {
        sum = sum + v1[i] * v2[last - i];
    }
    return sum;
}

int main(int argc, char *argv[]){
    Console_reader r;
    int trial;
    r.next_int(trial);
    for (unsigned int i = 0; i < trial; ++i) {
        int items;
        r.next_int(items);
        long tmp;
        vector<long> v1, v2;
        for (unsigned int i = 0; i < items && r.next_long(tmp); ++i) {
            v1.push_back(tmp);
        }
        for (unsigned int i = 0; i < items && r.next_long(tmp); ++i) {
            v2.push_back(tmp);
        }
        assert(v1.size() == v2.size() && v2.size() == items);
        long res = min_product(v1, v2);
        cout << "Case #" << i+1 << ": " << res << endl;
    }
	return 0;
}