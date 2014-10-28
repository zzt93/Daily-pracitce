#include "grade.h"
#include "median.h"
#include "Student_info.h"
#include <stdexcept>
#include <vector>

using std::domain_error; using std::vector;

double grade(double m, double f, double me){
	return 0.2*m + 0.4*f + 0.4*me;
}

double grade(double m, double f, const vector<double>& hw){
	if(hw.size() == 0){
		throw domain_error("no homework");
	}
	return grade(m, f, median(hw));
}

double grade(const Student_info& s){
	return grade(s.midterm, s.final, s.homework);
}

