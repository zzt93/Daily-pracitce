#include "Student_info.h"
#include <algorithm>
#include <vector>
#include <string>
#include "median.h"
#include "grade.h"
#include <stdexcept>
#include <numeric>

using namespace std;

bool did_all_hw(const Student_info& s){
    return ((find(s.homework.begin(), s.homework.begin(), 0)) == s.homework.end());
}

double grade_aus (const Student_info& s){
    try{
        return grade(s);
    } catch (domain_error){
        return grade(s.midterm, s.final, 0);
    }
}

double average(const vector<double>& v) {
    return accumulate(v.begin(), v.end(), 0.0)/v.size();
}

double average_grade(const Student_info& s){
    return grade(s.midterm, s.final, average(s.homework));
}

void write_analysis(ostream&out, const string& name,
    double analysis(const Student_info&),
    const vector<Student_info>& did, const vector<Student_info>& didnt){
    vector<double> tdid, tdidnt;
    transform(did.begin(), did.end(), back_inserter(tdid), analysis);
    transform(didnt.begin(), didnt.end(), back_inserter(tdidnt), analysis);
    
    out << name << ":median(did) = " << median(tdid) <<
        ",median(didnt) = " << median(tdidnt) << endl;
}

double optimistic_median(const Student_info& s){
    vector<double> nonzero;
    remove_copy(s.homework.begin(), s.homework.end(), back_inserter(nonzero), 0);

    if(nonzero.empty()){
        return grade(s.midterm, s.final, 0);
    }else {
        return grade(s.midterm, s.final, median(nonzero));
    }
}

int main(){
    vector<Student_info> did, didnt;

    Student_info student;
    while( read(cin, student) ){
        if(did_all_hw(student)){
            did.push_back(student);
        } else {
            didnt.push_back(student);
        }
    }
    if(did.empty()){
        cout << "No student did all homework" << endl;
        return 1;
    }
    if(didnt.empty()){
        cout << "Every student did all the homework" << endl;
    }

    write_analysis(cout, "median", grade_aus, did, didnt);
    write_analysis(cout, "average grade", average_grade, did, didnt);
    write_analysis(cout, "optimistic median", optimistic_median, did, didnt);
    return 0;
}