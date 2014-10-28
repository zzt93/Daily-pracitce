#include <stdio.h>
void testString ();
int main (int argc, char *argv[]) {
	char *p = "abcde";
	char a[] = "def";
	testString ();
}
void testString () {
	char a[] = "abcd";
	char *p = "abcde";
}