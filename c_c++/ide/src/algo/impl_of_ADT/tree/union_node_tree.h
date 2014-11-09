
typedef union {
    double d;
    char *str;
    int i;
    char c;
} Data;

typedef enum {DOUBLE, STRING, INT, CHAR} Type;
    
struct T{
    Data data;
    Type type;
    struct T *left;
    struct T *right;
};

typedef struct T TNode;