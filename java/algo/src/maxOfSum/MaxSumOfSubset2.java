
package maxOfSum;

/**
 * author:zzt
 * date:2014.7.8
 * description: count the practice.round1A2008.number of source file
 * version:0.2 -- trimming algorithm-2 ---can only find the maximum but not the index.
 */
import java.util.*;

public class MaxSumOfSubset2 {
	ArrayList<Double> set ;

	public MaxSumOfSubset2(ArrayList<Double> set) {
		this.set = set;
	}

	public double parts () {
		//trim the negative head if any
		int i;
		int j;
		if (set.get(0) <= 0) {
			for (i = 1; i < set.size() ; ++i) {
				if (set.get(i) > 0) {
					break;
				}
			}
			for (j = 0; j < i ; ++j) {
				set.remove(j);
			}
		}

		//trim the non-positive tail if any
		int lastIndex = set.size() - 1;
		if (set.get(lastIndex) <= 0) {
			for (i = lastIndex-1; i > 0 ; --i) {
				if (set.get(i) > 0) {
					break;
				}
			}
			for (j = lastIndex; j > i; --j) {
				set.remove(j);
			}
		}

		//check whether it is all be trimed already -- all negative or all positive case or {-, - , +, +, ...,+ , +, -}
		if (allPositive()) {
			return addAll(set);
		}
		
		//make the set no adjacent negative, positive numbers
		ArrayList<Double> temp = new ArrayList<Double>();
		double sum = 0;
		double number = 0;
		boolean sign = true; // means the practice.round1A2008.number is positive
		for (i = 0; i < set.size(); ++i) {
			number = set.get(i);
            //enter the following two blocks when the sign of practice.round1A2008.number is changed
			if (number < 0 && sign) {
				sign = false;
				temp.add(sum);
				sum = 0;
			}else if (number > 0 && !sign){
				sign = true;
				temp.add(sum);
				sum = 0;
			}
			sum += number;
		}
        temp.add(sum);//handle the last sum which will not put into list
		set.clear();
		set.addAll(0, temp);

		temp.clear();
		sum = set.get(0);
		for (i = 1; i+1 < set.size() ; i+=2) {
			number = set.get(i);
			if (number+sum < 0 || number+set.get(i+1)<=0) {
				temp.add(sum);
				sum = set.get(i+1);
			}else {
				sum = number + set.get(i+1) + sum;
			}
		}
        temp.add(sum);
		//find the max of sum
		double max = 0;
		for (Double d: temp) {
			if (d > max) {
				max = d;
			}
		}
		System.out.println(max);
        return max;
	}

	public double addAll(ArrayList<Double> set) {
		double sum = 0;
		for (Double d : set) {
			sum += d;
		}
		return sum;
	}

	public boolean allPositive() {
		for (Double d : set) {
			if (d < 0) {
				return false;
			}
		}
		return true;
	}
    public static void main (String[] argv) {
		new MaxSumOfSubset2(new ArrayList<Double>(Arrays.asList(12.0, 2.0, 3.0, -4.0, 5.0, -6.0, -7.0, 8.0, 9.0))).parts();
    }
}
