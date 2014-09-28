#include <iostream>

int main(int argc, char const *argv[])
{
	const std::string hello = "hello";
	// + operator has left-to-right associativity
	const std::string message = hello + " world" + "!";
	
	const std::string exclam = "!";
// [Error] invalid operands of types 'const char [6]' and 'const char [7]' to binary 'operator+'
//	const std::string message2 = "hello" + ",world" + exclam;

//  just put them next to each other
    const std::string message3 = "Hello" 
	                                    ",world" + exclam;
	std::cout<<message;
	std::cout<<message3;
	return 0;
}
