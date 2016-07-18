#include <iostream>
#include <algorithm>
#include <vector>
#include <set>

using std::endl;
using std::cout;

using namespace std;

class point {
private:
	int x;
	int y;
	int maxX;
	int minX;
public:
	point(int x, int y){
		this->x = x;
		this->y = y;
	}

	void set_maxx(int maxx, int minx) {
		maxX = maxx;
		minX = minx;
	}

	int get_y() {
		return y;
	}
	int get_x() {
		return x;
	}
	int get_max_x_dis() {
		if (maxX - x > x - minX) {
			return maxX - x;
		}
		return x - minX;
	}
};

int maxContainer(vector<int>& a) {
	vector<point*> points(a.size());
	set<int> nums;
	for(int i = 0; i < a.size(); i++) {
		points[i] = (new point(i + 1, a[i]));
		nums.insert(i + 1);
	}

	sort(points.begin(), points.end(), 
	[](point *p1, point* p2) {
		return p1->get_y() < p2->get_y();
	}
	);
	for(int i = 0; i < a.size() - 1; i++) {
		int maxX = *nums.rbegin();
		int minX = *nums.begin();
		points[i]->set_maxx(maxX, minX);
		nums.erase(points[i]->get_x());
	}

	int max = 0;
	for(int i = 0; i < a.size() - 1; i++) {
		int dis = points[i]->get_max_x_dis();
		int tmp = points[i]->get_y() * dis;
		if(tmp > max) {
			max = tmp;
		}
	}
	return max;
}

int main(int argc, char *argv[]){
	vector<int> a1 = {2,5, 3};
	cout << maxContainer(a1) << endl;
	return 0;
}
