#include <iostream>

// it is useless
#pragma pack(128)

struct INODE{
	//char filename[32];
    // file size
	size_t size;
    // ram or hda
    int dev_id;
    // store the index of disk block
	uint32_t index[15];
    // count of hard link
    int link_count;
    int type;
};

using std::cout;

int main(int argc, char *argv[]){
    cout << sizeof(struct INODE);
	return 0;
}