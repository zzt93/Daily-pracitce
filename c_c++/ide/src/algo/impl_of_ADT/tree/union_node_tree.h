
typedef union {
    double d;
    char *str;
    int i;
    char c;
} Data;

enum Type {DOUBLE, STRING, INT, CHAR};
    
struct T{
    Data data;
    Type type;
    struct T *left;
    struct T *right;
};

typedef struct T TNode;