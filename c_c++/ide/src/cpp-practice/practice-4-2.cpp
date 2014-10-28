#include <iostream>
#include <ios>
#include <iomanip>

using std::cout; using std::cin; using std::string; using std::endl;
using std::setw;

int main(int argc, char* argv[]){
	int i = 0, aim;
	cin >> aim;
	int gap, tem = aim;
	for(gap = 0; tem > 0; gap++){
		tem /= 10;
	}
	string si;
	/*version 1:
	for(; i < aim; ++i){
		si = std::to_string(i+1);
		cout << (i+1) << string(gap-si.size()+2, ' ')
		     << (i+1)*(i+1) << endl;
        }
	*/
	
	while(i < aim){
		++i;
		si = std::to_string(i);
		cout << i << setw(gap-si.size()+2) << ' ' << i*i << endl;
	}

}

