<<<<<<< HEAD
package maxOfSum; /**
 * author:zzt
 * date:2014.7.10
 * description: find the maximum of sum of a successive subset in a set; eg.{21, -9, -4, 28, -29, 45, 83}
 * version:0.1 -- trimming algorithm

 * update:2014.9.28
 * description: the initial algorithm is wrong, so try to fix it: change 59, 61, 63, 65, add 68

 */
import java.util.*;

public class maxOfSum {
	ArrayList<Double> set ;

	public maxOfSum (ArrayList<Double> set) {
		this.set = set;
	}

	public double parts () {
		//trim the non-positive head if any
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
		int lastIndex = set.size()-1;
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
		//collapse all adjacent positive number and negative number
		collapse(produceSeparator(true));
		collapse(produceSeparator(false));

		ArrayList<Double> temp = new ArrayList<Double>();
		double sum = set.get(0);
		double number = 0;
		for (i = 1; i+1 < set.size() ; i+=2) {
			number = set.get(i);
			if (number+sum<=0 || number+set.get(i+1)<=0) {
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

	public double addAll(ArrayList<Double> setD) {
		double sum = 0;
		for (Double d : setD) {
			sum += d;
		}
 		return sum;
	}

	public void collapse(int[] separator) {
		double sum = 0 ;
		ArrayList<Double> temp = new ArrayList<Double>();

		for (int i = 0 ; i+1 < separator.length ; ++i) {
			sum = 0;
			for (int j = separator[i]+1; j < separator[i+1] ; ++j) {
				sum += set.get(j);
			}
			if (separator[i]+1 != separator[i+1]) {//not the case of adjacent negative/positive number
			    temp.add(sum);
			}
			if (separator[i+1] != set.size()) {//keep the negative number in the mid of set and avoid adding the set.size()
				temp.add(set.get(separator[i+1]));
			}else{
                break;// Usually, the i+1 is far smaller than separator.length, so let the set.size to be the sentinel
            }
		}
		set.clear();
		set.addAll(0, temp);
	}

	public boolean allPositive() {
		for (Double d : set) {
			if (d < 0) {
				return false;
			}
		}
		return true;
	}

	public int[] produceSeparator(boolean positive) {
		int[] separator = new int[set.size()];
		//if positive is true, find the negative separator
		//in order to add the positive elements between separator[0] to separator[1]
		//---{se[0], positive1, ..., se[1]
		separator[0] = -1;
		int j = 1;
		if (positive) {
			for (int i = 0; i < set.size() ; ++i) {
				if (set.get(i) < 0) {
					separator[j++] = i;
				}
			}
		}else {
			for (int i = 0; i < set.size() ; ++i) {
				if (set.get(i) > 0) {
					separator[j++] = i;
				}
			}
		}
		separator[j] = set.size();
		return separator;
	}
    
	public static void main (String[] argv) {
		new maxOfSum (new ArrayList<Double>(Arrays.asList(12.0, 2.0, 3.0, -4.0, 5.0, -6.0, -7.0, 8.0, 9.0))).parts();
    }
}
=======
package maxOfSum; /**
 * author:zzt
 * date:2014.7.10
 * description: find the maximum of sum of a successive subset in a set; eg.{21, -9, -4, 28, -29, 45, 83}
 * version:0.1 -- trimming algorithm

 * update:2014.9.28
 * description: the initial algorithm is wrong, so try to fix it: change 59, 61, 63, 65, add 68

 */
import java.util.*;

public class maxOfSum {
	ArrayList<Double> set ;

	public maxOfSum (ArrayList<Double> set) {
		this.set = set;
	}

	public double parts () {
		//trim the non-positive head if any
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
		int lastIndex = set.size()-1;
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
		//collapse all adjacent positive number and negative number
		collapse(produceSeparator(true));
		collapse(produceSeparator(false));

		ArrayList<Double> temp = new ArrayList<Double>();
		double sum = set.get(0);
		double number = 0;
		for (i = 1; i+1 < set.size() ; i+=2) {
			number = set.get(i);
			if (number+sum<=0 || number+set.get(i+1)<=0) {
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

	public double addAll(ArrayList<Double> setD) {
		double sum = 0;
		for (Double d : setD) {
			sum += d;
		}
 		return sum;
	}

	public void collapse(int[] separator) {
		double sum = 0 ;
		ArrayList<Double> temp = new ArrayList<Double>();

		for (int i = 0 ; i+1 < separator.length ; ++i) {
			sum = 0;
			for (int j = separator[i]+1; j < separator[i+1] ; ++j) {
				sum += set.get(j);
			}
			if (separator[i]+1 != separator[i+1]) {//not the case of adjacent negative/positive number
			    temp.add(sum);
			}
			if (separator[i+1] != set.size()) {//keep the negative number in the mid of set and avoid adding the set.size()
				temp.add(set.get(separator[i+1]));
			}else{
                break;// Usually, the i+1 is far smaller than separator.length, so let the set.size to be the sentinel
            }
		}
		set.clear();
		set.addAll(0, temp);
	}

	public boolean allPositive() {
		for (Double d : set) {
			if (d < 0) {
				return false;
			}
		}
		return true;
	}

	public int[] produceSeparator(boolean positive) {
		int[] separator = new int[set.size()];
		//if positive is true, find the negative separator
		//in order to add the positive elements between separator[0] to separator[1]
		//---{se[0], positive1, ..., se[1]
		separator[0] = -1;
		int j = 1;
		if (positive) {
			for (int i = 0; i < set.size() ; ++i) {
				if (set.get(i) < 0) {
					separator[j++] = i;
				}
			}
		}else {
			for (int i = 0; i < set.size() ; ++i) {
				if (set.get(i) > 0) {
					separator[j++] = i;
				}
			}
		}
		separator[j] = set.size();
		return separator;
	}
    
	public static void main (String[] argv) {
		new maxOfSum (new ArrayList<Double>(Arrays.asList(12.0, 2.0, 3.0, -4.0, 5.0, -6.0, -7.0, 8.0, 9.0))).parts();
    }
}
>>>>>>> c2cda320b02ff013ad6a1e316c0b332d67e4583c
