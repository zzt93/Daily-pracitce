#include "utility/daliy.hpp"
#include "utility/console_reader.cpp"
#include "utility/my_algo.hpp"
#include <set>
#include <algorithm>

using std::set;
using std::binary_search;
using std::sort;

struct IntWithIndex {
     int i;
     int in;

    IntWithIndex(int i1, int i2) {
        i = i1;
        in = i2;
    }
 };

 bool comp(IntWithIndex i1, IntWithIndex i2) {
     return i1.i < i2.i;
}

vector<int> two_sum(int sum, vector<IntWithIndex>& it) {
    vector<int> res;
    sort(it.begin(), it.end(), comp);
    for (unsigned int i = 0; i < it.size(); ++i) {
        int aim = sum - it[i].i;
        if (2 * it[i].i > sum) {
            assert(false);
        }
        auto ano = binary_search_index(it.begin() + i + 1, it.end(), IntWithIndex(aim, -1), comp);
        if (ano != it.end()) {
            res.push_back(it[i].in + 1);
            res.push_back(ano->in + 1);
            return res;
        }
    }
    assert(false);
}

int main(int argc, char *argv[]){
    Console_reader r;
    int trial;
    r.next_int(trial);
    for (unsigned int i = 0; i < trial; ++i) {
        int money, items;
        r.next_int(money);
        r.next_int(items);
        int tmp;
        vector<IntWithIndex> it;
        for (unsigned int i = 0; i < items && r.next_int(tmp); ++i) {
            it.push_back(IntWithIndex(tmp, i));
        }
        assert(it.size() == items);
        vector<int> res = two_sum(money, it);
        sort(res.begin(), res.end());
        cout << "Case #" << i+1 << ": " << res[0] << " " << res[1] << endl;
    }
	return 0;
}
