/*************************************************************************
 * Name:zzt
 * Email:zengzt@gmail.com
 *
 * Compilation: 1
 * Execution: ./median < Median.txt
 * Dependencies: see include
 *
 * Description: The goal of this problem is to implement the "Median Maintenance" algorithm (covered in the Week 5 lecture on heap applications).
 * The text file contains a list of the integers from 1 to 10000 in unsorted order; you should treat this as a stream of numbers, arriving one by one. Letting xi denote the ith number of the file, the kth median mk is defined as the median of the numbers x1,…,xk.
 * (So, if k is odd, then mk is ((k+1)/2)th smallest number among x1,…,xk;
 * if k is even, then mk is the (k/2)th smallest number among x1,…,xk.)

 * In the box below you should type the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 digits). That is, you should compute (m1+m2+m3+⋯+m10000)mod10000.
 *
 *************************************************************************/

#include "utility/daliy.hpp"
#include "utility/console_reader.cpp"
#include <vector>
#include <algorithm>

using std::vector;
using std::push_heap;

vector<int> max_heap;
vector<int> min_heap;
int median = 0;

bool large(int a, int b) {
    return a > b;
}

static void push_min(int v) {
    min_heap.push_back(v);
    push_heap(min_heap.begin(), min_heap.end(), large);
}
static void push_max(int v) {
    max_heap.push_back(v);
    push_heap(max_heap.begin(), max_heap.end());
}

static int extract_max() {
    pop_heap(max_heap.begin(), max_heap.end());
    int res = max_heap[max_heap.size()-1];
    max_heap.erase(max_heap.begin()+max_heap.size()-1);
    return res;
}
static int extract_min() {
    pop_heap(min_heap.begin(), min_heap.end(), large);
    int res = min_heap[min_heap.size()-1];
    min_heap.erase(min_heap.begin()+min_heap.size()-1);
    return res;
}
int median_heap(int v){
    if (v > median) {
        push_min(v);
    } else {
        push_max(v);
    }

    if (max_heap.size() < min_heap.size()) {
        int i = extract_min();
        push_max(i);
    } else if (max_heap.size() > min_heap.size() + 1) {
        int i = extract_max();
        push_min(i);
    }
    median = max_heap[0];
    return median;
}

int median_bst(int i) {
    assert(false);
    return 0;
}

int sum_and_modulo(vector<int>& v, int target) {
    long sum = 0;
    for(int i: v) {
        sum += i;
    }
    return sum%target;
}

int main(int argc, char *argv[])
{
    Console_reader r;
    vector<int> nums;
    int i = 0;
    while (r.next_int(i)){
        int me = median_heap(i);
        nums.push_back(me);
    }
    cout << sum_and_modulo(nums, 10000) << endl;
    return 0;
}