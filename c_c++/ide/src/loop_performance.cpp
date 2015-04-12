#include <iostream>
#include <iomanip>
#include <cmath>
#include <string>

//#define TBB_TIMING

#ifdef TBB_TIMING	
#include <tbb/tick_count.h>
using tbb::tick_count;
#else
#include <time.h>
#endif




using namespace std;



//#define preallocate_memory new_cont

enum {new_cont,new_sep};



double *a1,*b1,*c1,*d1;


void allo(int cont,int n){

	switch(cont){
	case new_cont:
		a1 = new double[n*4];
		b1 = a1 + n;
		c1 = b1 + n;
		d1 = c1 + n;
		break;
	case new_sep:
		a1 = new double[n];
		b1 = new double[n];
		c1 = new double[n];
		d1 = new double[n];
		break;
	}
	for(int i=0;i<n;i++){
		a1[i]=1.0;d1[i]=1.0;c1[i]=1.0;b1[i]=1.0;
	}
}

void ff(int cont){

	switch(cont){
	case new_sep:
		delete[] b1;
		delete[] c1;
		delete[] d1;
	case new_cont:
		delete[] a1;
	}
	
}


double plain(int n,int m,int cont,int loops)
{
#ifndef preallocate_memory
	allo(cont,n);
#endif

#ifdef TBB_TIMING	
	tick_count t0 = tick_count::now();
#else
	clock_t start = clock();
#endif
	
	

	if(loops==1)
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++){
				a1[j] += b1[j];
				c1[j] += d1[j];
		}
	else
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				a1[j] += b1[j];
			}
			for(int j=0;j<n;j++){
				c1[j] += d1[j];
			}
		}
		

	double ret;

#ifdef TBB_TIMING	
	tick_count t1 = tick_count::now();
	ret=2.0*double(n)*double(m)/(t1-t0).seconds();
#else
	clock_t end = clock();
	ret=2.0*double(n)*double(m)/(double)(end - start) *double(CLOCKS_PER_SEC);
#endif
	
#ifndef preallocate_memory
	ff(cont);
#endif

	return ret;
}


void main(){
	
	freopen( "c:\\test.csv" ,"w",stdout);

	char* s=" ";

	string na[2] ={"new_cont","new_sep"};

	cout<<"n";

	for(int j=0;j<2;j++)
		for(int i=1;i<=2;i++)
#ifdef preallocate_memory
			cout<<s<<i<<"_loops_"<<na[preallocate_memory];
#else
			cout<<s<<i<<"_loops_"<<na[j];
#endif
			
	cout<<endl;

	long long nmax = 1000000;

#ifdef preallocate_memory
	allo(preallocate_memory,nmax);
#endif
	
	for(long long n=1;n<nmax;n=max(n+1,long long(n*1.2))){
		const long long m=10000000/n;
		cout<<n;
		for(int j=0;j<2;j++)
			for(int i=1;i<=2;i++)
				cout<<s<<plain(n,m,j,i);
		cout<<endl;
	}
}
