#include <stdio.h>
#include <stdlib.h>

int main()
{
  int bytes_read;
  size_t nbytes = 100;
  char *my_string;

  puts ("Please enter a line of text.");

  /* These 2 lines are the heart of the program. */
  my_string = (char *) malloc (nbytes + 1);
  bytes_read = getline (&my_string, &nbytes, stdin);

  if (bytes_read == -1)
    {
      puts ("ERROR!");
    }
  else
    {
      puts ("You typed:");
      puts (my_string);
    }


int i = 0; 
for (; i < bytes_read; ++i ) {
	if (my_string[i] == EOF){
		puts("it has EOF");
	}else {
		puts("it don't");
	}
}
  return 0;
}

