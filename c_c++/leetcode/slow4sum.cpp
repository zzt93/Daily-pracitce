#include <iostream>
#include <vector>
#include <set>
#include <algorithm>
#include <iterator>

using std::endl;
using std::cout;

using namespace std;

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

	set<vector<int>, Less> ss;

	sort(nums.begin(), nums.end());
    for(int f = 0; f < n; f++) {
		for(int s = f + 1; s < n; s++) {
			for(int t = s + 1; t < n; t++) {		
				int tmp = nums[f] + nums[s] + nums[t];
				auto find = binary_search(nums.begin() + t + 1, nums.end(), target - tmp);
				if (find) {
					ss.insert({nums[f], nums[s], nums[t], target-tmp});
				}
			}
		}
	}
	vector<vector<int>> ret;
	copy(ss.begin(), ss.end(), back_inserter(ret));
	return ret;
}

int main(int argc, char *argv[]){
	vector<int> v = {1, 0, -1, 0, 2, -2, 3, -3};
	fourSum(v, 0);
	return 0;
}
