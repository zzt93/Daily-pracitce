#include <iostream>
#include <vector>
#include <Student_info.h>
#include <algorithm>

using namespace std;

int main(){
}

template <class Ana>
double analysis(const vector<Student_info>& students, Ana f){
    vector<double> grades;
    transform(students.begin(), students.end(), bac_inserter(grades), f);
    return median(grades);
}