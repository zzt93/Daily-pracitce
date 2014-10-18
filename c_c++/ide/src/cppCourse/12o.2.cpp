#include <iostream>
#include <istream>
#include <fstream>
#include <cstdlib>
#include <climits>
#include <cstring>


/*
  bug:
  1. linkedlist using Node*
  2. if different block using same variables, remember assign right initial values
  2.1 eg. two similar loops
  2.2 eg. nested loops
  
*/
const int yearNum = 13;

using namespace std;
struct Node{
	int y, t;
	Node *next;
};

bool initial(Node *s, Node **d, int limits){
    int i = 0;
    for(; i < limits; ++i){
        d[i] = &s[i];
    }
}
void copyP(Node **s, Node **d, int limits){
    int i = 0;
    for(; i < limits; ++i){
        d[i] = s[i];
    }
}
int main(){
	ifstream data("data.txt");
	ofstream mapFile, shf, red;
	if(data.is_open()){
        char str[256];
		Node head[yearNum] = {0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,
                         0, 0, NULL,};
		Node *pHead[yearNum] ;
        initial(head, pHead, yearNum);
        int year, tem;
		char cY[4], cT[5];

		while(data.getline(str, 256, '\n')){
			if( str[92] == '0' ||
				str[92] == '1' ||
				str[92] == '4' ||
				str[92] == '5' ||
				str[92] == '9' ){
				
				strncpy(cY, &str[15], 4);
				strncpy(cT, &str[87], 5);
				year = atoi(cY);
				tem = atoi(cT);
                if(tem != 9999 && tem != -9999){
                    Node *t = (Node *)malloc(sizeof(Node));
                    t->y = year;
                    t->t = tem;
                    t->next = pHead[year-2000];
                    pHead[year-2000] = t;
                }
            }
		}
		data.close();
		mapFile.open("131250072.map");
        Node *duHead[yearNum] ;
        copyP(pHead, duHead, yearNum);
        int in = 0;
        for(;in < yearNum ; ++in){
            while(pHead[in]->next != NULL){
                mapFile << pHead[in]->y << "," << pHead[in]->t << endl;
                pHead[in] = pHead[in]->next;
            }
        }

        //shuffle
		int i = 0;
        int first;
        shf.open("131250072.shf");
		for (; i < yearNum; ++i){
            first = 0;
            pHead[i] = duHead[i];
			while(pHead[i]->next != NULL){
				if(pHead[i]->y == i+2000){
					++first;
					if(first == 1){
						shf << pHead[i]->y << "[";
					}
                    if(pHead[i]->next->next != NULL){
                        shf << pHead[i]->t << ",";
                    }
				}
                pHead[i] = pHead[i]->next;
			}
			if(first != 0){
				shf << "]" << endl;
			}
		}
		shf.close();

		//reduce
		i = 0;
		int max = -INT_MAX;
        red.open("131250072.red");
		for (; i < yearNum; ++i){
            first = 0;
            pHead[i] = duHead[i];
            max = -INT_MAX;
			while(pHead[i]->next != NULL){
				if(pHead[i]->y == i+2000){
					++first;
					if(first == 1){
						red << pHead[i]->y << "," ;
					}
					if(pHead[i]->t > max){
						max = pHead[i]->t;
					}
				}
                pHead[i] = pHead[i]->next;
			}
			if(first != 0){
				red << max << endl;
			}
		}
		red.close();

	} else {
		cout << "can't open file" << endl;
	}
	return 0;
}
