
typedef union {
    double d;
    char c;
    char *str;
    int i;
} Data;

typedef enum {DOUBLE, STRING, INT, CHAR} Type;
typedef enum {PREORDER, INORDER, POSTORDER} Order;

struct T{
    Data data;
    Type type;
    struct T *left;
    struct T *right;
};

typedef struct T TNode;