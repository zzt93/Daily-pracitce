#include "Heap.hpp"
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using std::vector;
using std::cout;
using std::endl;
using std::ifstream;
using std::ofstream;
/*
  new version: choose from children

*/
template <class T>
void heap_sort2(vector<T> t){
    
}

/*
  initial algorithm about heap sort1
*/
template <class T>
void heap_sort1(vector<T> t){

}

bool large_fun(int t1, int t2) {
    return t1>t2;
}

int main(int argc, char *argv[])
{
    if (argc != 3){
        cout << "Usage: ./sort max/min int/double" << endl;
        return 0;
    }
    ifstream nums ("random numbers");
    if(nums.is_open()){
        // read numbers from file
        vector<int> vec;
        int temp = 0;
        while (nums >> temp){
            vec.push_back(temp);
        }
        nums.close();

        // make heap
        bool max = false;
        if (argv[1][1] == 'a') {
            max = true;
        }
        Heap<int> heap(max, vec);
        auto tmp = heap.get_node();
        cout << std::is_heap(tmp.begin(), tmp.end(), large_fun) << endl;
        cout << heap;
        // sort and output
    } else {
        cout << "Fail to open file" << endl;
    }
    return 0;
}
