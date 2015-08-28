#include <stdio.h>
#include <stdlib.h>
#include <stddef.h>

typedef enum {
    NOT_NODE,
    DIR,
    PLAIN,
} File_e;

struct test_t {
    unsigned int size;
    // ram or hda
    int dev_id;
    // store the index of disk block
	unsigned int index[15];
    // count of hard link
    int link_count;
    File_e type;
} __attribute__((aligned(128)));

static char assert_iNode_size[sizeof(struct test_t) % 128 == 0 ? 1 : -1];

int main()
{
    printf("%lu ", offsetof(struct test_t, type));
    printf("%lu\n", sizeof(struct test_t));
    return 0;
}