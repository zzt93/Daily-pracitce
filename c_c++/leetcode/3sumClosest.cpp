#include <iostream>
#include <algorithm>
#include <cassert>
#include <vector>
#include <set>
#include <climits>

using std::endl;
using std::cout;

using namespace std;

void splitSum(int sum, vector<int>& f, vector<int>& s) {
	int end = (sum & 1) == 0 ? sum / 2 : sum / 2 + 1;
	for(int i = 0; i < end; i++) {
		f.push_back(i);
		s.push_back(sum - i);
	}
}

int binarySearch(vector<int>& a, int fromIndex, int toIndex,
                                     int key) {
    int low = fromIndex;
    int high = toIndex - 1;

    while (low <= high) {
        int mid = (low + high) >> 1;
        int midVal = a[mid];

        if (midVal < key)
            low = mid + 1;
        else if (midVal > key)
            high = mid - 1;
        else
            return mid; // key found
    }
    return -(low + 1);  // key not found.
}

int sumClosest(vector<int>& nums, int aim) {
	int len = nums.size();
	sort(nums.begin(), nums.end());
	int end = nums.size() * 2 - 5;
	int start = 1;

	int gap = INT_MAX;
	int res = INT_MAX;
	for (int i = start; i <= end; i++) {
		vector<int> f, s;
		splitSum(i, f, s);
		for(int j = 0; j < f.size(); j++) {
			int twoSum = nums[f[j]] + nums[s[j]];
			int left = aim - twoSum;
			assert(s[j] > f[j]);
			int target = binarySearch(nums, s[j] + 1, len, left);
            if (target >= 0) { // find the exact match
                return aim;
            } else {
                // change to insert point
                target = -target - 1;
                if (target > s[j] + 1) { // has floor
                     int floor = nums[target - 1];
                     int floorGap = left - floor;
                    if (gap > floorGap) {
                        gap = floorGap;
                        res = aim - gap;
                    }
                }
                if (target < len) { // has ceiling
                    int ceiling = nums[target];
                     int ceilGap = ceiling - left;
                    if (gap > ceilGap) {
                        gap = ceilGap;
                        res = aim + gap;
                    }
                }
            }
		}
	}
	return res;
}

int main(int argc, char *argv[]){
	vector<int> nums = {0, 1, 2, -1};
	cout << sumClosest(nums, 0);
	return 0;
}
