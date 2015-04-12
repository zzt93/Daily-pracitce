#include <iostream>
#include <istream>
#include <fstream>
#include <cstdlib>
#include <climits>
#include <cstring>


/*
  bug:
  1. linkedlist using node*
  2. if different block using same variables, remember assign right initial values
  2.1 eg. two similar loops
  2.2 eg. nested loops
  
*/
const int yearnum = 13;

using namespace std;
struct node{
	int y, t;
	node *next;
};

void initial(node *s, node **d, int limits){
    int i = 0;
    for(; i < limits; ++i){
        d[i] = &s[i];
    }
}
void copyp(node **s, node **d, int limits){
    int i = 0;
    for(; i < limits; ++i){
        d[i] = s[i];
    }
}
int main(){
	ifstream data("data.txt");
	ofstream mapfile, shf, red;
	if(data.is_open()){
        char str[256];
		node head[yearnum] = {0, 0, NULL,
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
		node *phead[yearnum] ;
        initial(head, phead, yearnum);
        int year, tem;
		char cy[4], ct[5];

		while(data.getline(str, 256, '\n')){
			if( str[92] == '0' ||
				str[92] == '1' ||
				str[92] == '4' ||
				str[92] == '5' ||
				str[92] == '9' ){
				
				strncpy(cy, &str[15], 4);
				strncpy(ct, &str[87], 5);
				year = atoi(cy);
				tem = atoi(ct);
                if(tem != 9999 && tem != -9999){
                    node *t = (node *)malloc(sizeof(node));
                    t->y = year;
                    t->t = tem;
                    t->next = phead[year-2000];
                    phead[year-2000] = t;
                }
            }
		}
		data.close();
		mapfile.open("131250072.map");
        node *duhead[yearnum] ;
        copyp(phead, duhead, yearnum);
        int in = 0;
        for(;in < yearnum ; ++in){
            while(phead[in]->next != NULL){
                mapfile << phead[in]->y << "," << phead[in]->t << endl;
                phead[in] = phead[in]->next;
            }
        }

        //shuffle
		int i = 0;
        int first;
        shf.open("131250072.shf");
		for (; i < yearnum; ++i){
            first = 0;
            phead[i] = duhead[i];
			while(phead[i]->next != NULL){
				if(phead[i]->y == i+2000){
					++first;
					if(first == 1){
						shf << phead[i]->y << "[";
					}
                    shf << phead[i]->t;
                    if(phead[i]->next->next != NULL){
                        shf << ",";
                    }
				}
                phead[i] = phead[i]->next;
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
		for (; i < yearnum; ++i){
            first = 0;
            phead[i] = duhead[i];
            max = -INT_MAX;
			while(phead[i]->next != NULL){
				if(phead[i]->y == i+2000){
					++first;
					if(first == 1){
						red << phead[i]->y << "," ;
					}
					if(phead[i]->t > max){
						max = phead[i]->t;
					}
				}
                phead[i] = phead[i]->next;
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
