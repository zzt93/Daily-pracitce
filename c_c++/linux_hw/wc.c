#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define SPACE ' '

void wc1(const char* name) {
    FILE * fp;
    size_t len = 256;
    char *line = malloc(len);
    ssize_t read;

    fp = fopen(name, "r");
    if (fp == NULL) {
        exit(EXIT_FAILURE);
    }

    int lineCount = 0;
    int wordCount = 0;
    int byteCount = 0;
    while ((read = getline(&line, &len, fp)) != -1) {
        //printf("Retrieved line of length %zu :\n", read);
        lineCount++;
        int i;
        int last = SPACE;
        for (i = 0; i < read; i++) {
            if (i >= 1) {
                last = line[i - 1];
            }
            if (isspace(line[i]) && !isspace(last)) {
                wordCount++;
            }
        }
        byteCount += read;
        //printf("%s", line);
    }
    printf("%s: %d, %d, %d\n", name, byteCount, wordCount, lineCount);
    fclose(fp);
    exit(EXIT_SUCCESS);
}

void wc(const char* name) {
    FILE * fp;

    fp = fopen(name, "r");
    if (fp == NULL) {
        exit(EXIT_FAILURE);
    }

    int lineCount = 0;
    int wordCount = 0;
    int byteCount = -1;
    char c = SPACE;
    // init it with a char which is not space
    char last;
    do {
        byteCount++;
        last = c;
        c = (char)fgetc(fp);
        if (isspace(c)) {
            if (!isspace(last)) {
                wordCount++;
            }
            if(c == '\n'){
                lineCount++;
            }
        }
    } while(c != EOF);
    printf("%s: %d, %d, %d\n", name, byteCount, wordCount, lineCount);
    fclose(fp);
}

int main(int argc, char *argv[]){
    if (argc < 2) {
        puts("too few arguments");
        exit(EXIT_FAILURE);
    }
    wc(argv[1]);
    wc1(argv[1]);
	return 0;
}