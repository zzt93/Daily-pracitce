#include "Heap.hpp"
#include <iostream>
#include <fstream>
#include <vector>

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
            //cout << temp << endl;
            vec.push_back(temp);
        }
        nums.close();

        // make heap
        bool max = false;
        if (argv[1][1] == 'a') {
            max = true;
        }
        Heap<int> heap(max, vec);
        cout << heap ;
        // sort and output
    } else {
        cout << "Fail to open file" << endl;
    }
    return 0;
}
