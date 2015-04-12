#include <iostream>
#include <string>

int main(int argc, char const *argv[])
{
	/* 1.the buffer is full
	   2.read data from in stream
	   3.we request it  a. std::endl  b. 
	   */
	std::cout << "Please enter you name: " ;
	std::string name;
	std::cin >> name;
	while (std::cin >> name){
	}
	std::cout << "Hello, "  << name  << "!" << std::endl;
	
	std::cout << "test--";
	std::cout << "test2--";
	return 0;
}

