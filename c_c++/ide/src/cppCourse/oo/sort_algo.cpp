#include <iostream>
#include <vector>
#include <fstream>
#include <algorithm>

using std::cout;
using std::vector;
using std::endl;
using std::ifstream;
using std::ostream;

using std::sort;
using std::for_each;

void print(int i){
    cout << i << " " ;
}

int main(int argc, char *argv[])
{
    ifstream data("data_for_sort");
    if (data){
        int tem_i = 0;
        vector<int> vec;
        while(data >> tem_i){
            vec.push_back(tem_i);
        }
        sort(vec.begin(), vec.end());
        for_each(vec.begin(), vec.end(), print);
        cout << *std::min_element(vec.begin(), vec.end());
    } else {
        cout << "fail to open file";
    }
    return 0;
}
