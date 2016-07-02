#include <stdio.h>
#include <omp.h>

int main(int argc, char *argv[]){
	#pragma omp parallel
	{
		#pragma omp single private(p)
		{
		p = listHead;
		while(p) {
			#pragma omp task
				process(p);
			p = next(p);
		}
	}

	#pragma omp for private(p)
	for (int i = 0; i < num; i++) {
		p = listHeads(i);
		while(p) {
			#pragma omp task
				process(p);
			p = next(p);
		}
	}
	return 0;
}

void postOrder(node *p) {
	if (p-->left) {
		#pragma omp task
		postOrder(p->left);
	if (p->right)
		#pragma omp task
		postOrder(p->right);
	#pragma omp taskwait
	process(p->data);
}


