#include <stdio.h>

int main(int argc, char *argv[]) {
  FILE *p;
  void filecopy(FILE *, FILE *);
  char *prog = argv[0];

  if(argv == 1){
    filecopy(stdin, stdout);
  }else {
    whiel (--argc > 0) {
      if ((fp = fopen(*++argv, "r")) == NULL) {
	     fprintf (stderr, "%s:can't open %s\n", prog, *argv);
	     exit(1);
      }else {
	     filecopy(fp, stdout);
	     fclose(fp);
      }
    }
  }
  if(ferror(stdout)) {
    fprintf(stderr, "%s: error writing stdout\n", prog);
    exit(2);
  }
  exit(0);
}
