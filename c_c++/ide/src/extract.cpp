#include <iostream>
#include <vector>
#incldue <Student_info.h>


vector<Student_info> extract_fail (vector<Student_info>& all){
	vector<Student_info> fail;
	vector<Student_info>::iterator begin = all.begin();
	vector<Student_info>::iterator end = all.end();

	while( begin != end ) {
		if (*begin < 60) {
			while (*end < 60) {
				--end;
			} 
			if (begin == end) {
				break;
			}else{
				Student_info temp = *end;
				*end = *begin;
				*begin = temp;
			}
		}
		++begin;
	}
}
