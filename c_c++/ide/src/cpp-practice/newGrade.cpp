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

double median_analysis(const vector<Student_info>& students) {
    vector<double> grades;
    transform(students.begin(), students.end(), back_inserter(grades), grade_aus);
    return median(grades);
}
double average(const vector<double>& v) {
    return accumulate(v.begin(), v.end(), 0.0)/v.size();
}

double average_grade(const Student_info& s){
    return grade(s.midterm, s.final, average(s.homework));
}

double average_analysis(const vector<Student_info>& students) {
    vector<double> grades;
    transform(students.begin(), students.end(), back_inserter(grades), average_grade);
    return median(grades);
}

void write_analysis( ostream&out, const string& name,
    double analysis(const vector<Student_info>&),
    const vector<Student_info>& did, const vector<Student_info>& didnt){
    
    out << name << ":median(did) = " << analysis(did) <<
        ",median(didnt) = " << analysis(didnt) << endl;
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
double optimistic_median_analysis(const vector<Student_info>& s){
    vector<double> grades;
    transform(s.begin(), s.end(), back_inserter(grades), optimistic_median);
    return median(grades);
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

    write_analysis(cout, "median", median_analysis, did, didnt);
    write_analysis(cout, "average", average_analysis, did, didnt);
    write_analysis(cout, "median of homework turned in", optimistic_median_analysis, did, didnt);

    return 0;
}