/*************************************************************************
 * Name:zzt
 * Email:zengzt@gmail.com
 *
 * Compilation: 1
 * Execution: 2sum < data_of_number
 * Dependencies: see include
 *
 * Description: The goal of this problem is to implement a variant of the 2-SUM algorithm
 * (covered in the Week 6 lecture on hash table applications).

 * The file contains 1 million integers, both positive and negative (there might be some repetitions!).This is your array of integers, with the ith row of the file specifying the ith entry of the array.

 * Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such that there are distinct numbers x,y in the input file that satisfy x+y=t. (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)
 *
 *************************************************************************/

#include <iterator>
#include <algorithm>
#include "utility/console_reader.cpp"
#include "utility/input_handler.hpp"
#include "utility/daliy.hpp"
#include <vector>
#include <set>

using std::back_inserter;
using std::vector;
using std::set;
using std::sort;
using std::unique;

static const int MISMATCH = 1;

int sum_range(vector<long>& nums, int low, int hi) {
    set<long> set;
    for (unsigned int i = 0; i < nums.size(); ++i) {
        long g1 = hi - nums[i];
        long g2 = low - nums[i];
        assert (g1 > g2);
        auto lar = upper_bound(nums.begin()+i+MISMATCH, nums.end(), g1);// find the first element larger than g1
        auto sma = lower_bound(nums.begin()+i+MISMATCH, lar, g2);// find the first element not less than g2
        if (set.size() == hi - low + 1) {
            break;
        }
        /*
          if (nums[i] >= 0 && sma == lar) {
          break;
          }
        */
        for (; sma != lar; sma++) {
            long tmp = *sma + nums[i];
            assert(tmp <= hi && tmp >= low);
            set.insert(tmp);
        }
    }
    return set.size();
}

int main(int argc, char *argv[])
{
    Console_reader r;
    Input_handler in;
    vector<long> nums;
    long i = 0;
    while (r.next_long(i)){
        nums.push_back(i);
    }
    sort(nums.begin(), nums.end());
    auto last = unique(nums.begin(), nums.end());
    nums.erase(last, nums.end());
    cout << sum_range(nums, -10000, 10000) << endl;
    return 0;
}