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

using namespace std;
struct Node{
	int y, t;
	Node *next;
};

int main(){
	ifstream data("data.txt");
	ofstream mapFile, shf, red;
	if(data.is_open()){
        char str[256];
		Node head = {0, 0, NULL};
		Node *pHead ;
		pHead = &head;

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
                    t->next = pHead;
                    pHead = t;
                }
            }
		}
		data.close();
		mapFile.open("131250072.map");
        Node *duHead = pHead;
		while(pHead->next != NULL){
			mapFile << pHead->y << "," << pHead->t << endl;
            pHead = pHead->next;
		}

		//shuffle
		int i = 0;
        int first;
        shf.open("131250072.shf");
		for (; i < 13; ++i){
            first = 0;
            pHead = duHead;
			while(pHead->next != NULL){
				if(pHead->y == i+2000){
					++first;
					if(first == 1){
						shf << pHead->y << "[";
					}
					shf << pHead->t << ",";
				}
                pHead = pHead->next;
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
		for (; i < 13; ++i){
            first = 0;
            pHead = duHead;
            max = -INT_MAX;
			while(pHead->next != NULL){
				if(pHead->y == i+2000){
					++first;
					if(first == 1){
						red << pHead->y << "," ;
					}
					if(pHead->t > max){
						max = pHead->t;
					}
				}
                pHead = pHead->next;
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
