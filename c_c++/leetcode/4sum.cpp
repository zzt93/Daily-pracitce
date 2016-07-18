#include <iostream>
#include <vector>
#include <set>
#include <iterator>
#include <cassert>
#include <algorithm>

using std::endl;
using std::cout;

using namespace std;

class twoSum {
public:
	int fi;
	int fn;
	int si;
	int sn;
	int sum;
	twoSum(int i, int n, int ii, int nn) {
		fi = i;
		fn = n;
		si = ii;
		sn = nn;
		sum = n + nn;
	}

	bool overlap(const twoSum* s) {
		return fi == s->fi || fi == s->si || si == s->fi || si == s->si;
	}
	void setsum(int s) {
		sum = s;
	}
};

bool func(const twoSum* s1, const twoSum* s2) {
	return s1->sum < s2->sum;
}

struct Less{
    bool operator()(const vector<int>& a, const vector<int>& b)
    {  
/*		vector<int> aa = a;
		vector<int> bb = b;
		sort(aa.begin(), aa.end());
		sort(bb.begin(), bb.end());
*/
		for(int i = 0; i < a.size(); i++) {
			if(a[i] < b[i]) {
				return true;
			} else if (a[i] > b[i]) {
				return false;
			}
		}
		return false;
    }   
};

vector<vector<int>> fourSum(vector<int>& nums, int target) {
	int n = nums.size();
	int nsquare = n * (n - 1) / 2;
	vector<twoSum*> v(nsquare);

	int vi = 0;
    for(int f = 0; f < n; f++) {
		for(int s = f + 1; s < n; s++) {
			v[vi++] = (new twoSum(f, nums[f], s, nums[s]));
		}
	}

	set<vector<int>, Less> ss;
	sort(v.begin(), v.end(), func);
	twoSum tmp(0, 0, 0, 0);
	for(int i = 0; i < nsquare; i++) {
		twoSum* num = v[i];
		int aim = target - num->sum;
		tmp.setsum(aim);
		vector<twoSum*>::iterator start = v.begin() + i + 1, last = v.end();
		vector<twoSum*>::iterator f = lower_bound(start, last, &tmp, func);
		twoSum *find = *f;

		while(f != last && find->sum == target - num->sum) {
			if(!find->overlap(num)) {
				vector<int> v = {num->fn, num->sn, find->fn, find->sn};
				sort(v.begin(), v.end());
				auto pair = ss.insert(v);
				for(int& i: v) {
					cout << i << ' ';
				}
				cout << pair.second << endl;
			}
			f = lower_bound(f + 1, last, &tmp, func);
			find = *f;
		}
	}
		
	vector<vector<int>> ret;
	copy(ss.begin(), ss.end(), back_inserter(ret));
	return ret;
}

int main(int argc, char *argv[]){
	vector<int> v = {1, 0, -1, 0, 2, -2};
	fourSum(v, 0);
	return 0;
}
