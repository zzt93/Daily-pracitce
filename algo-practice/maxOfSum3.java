/**
 * author:zzt
 * date:2014.8.13, 9.28
 * description: find the maximum of sum of a successive subset in a set; eg.{21, -9, -4, 28, -29, 45, 83}
 * version:0.3 -- collapse algorithm -- can also return the index of begin and end
 */
import java.util.*;

public class maxOfSum {
	ArrayList<Double> set ;

	public maxOfSum (ArrayList<Double> set) {
		this.set = set;
	}

	public void parts () {
        int i;
		int leftI = 0;
		int rightI = 0;
        for(i = 0; i < set.size(); ++i){
			if (set.get(i) > 0) {
			    break;
			}
		}   
		//make the set no adjacent positive, negative number 
	    HashMap<Double, double[]> tMap = new HashMap<Double, double[]>();
        ArrayList<Double> temp = new ArrayList<Double>();
		double sum = 0;
		double number = 0;
		boolean sign = true; // means the number is positive
        int j = i;//record the begin index of contiguous positive, negative number
		for (; i < set.size(); ++i) {
			number = set.get(i);
            //enter the following two blocks when the sign of number is changed
			if (number < 0 && sign) {
				sign = false;
                tMap.put(sum, new double[]{j, i});// [j, i) is the interval
				temp.add(sum);
                j = i;
				sum = 0;
			}else if (number > 0 && !sign){
				sign = true;
                tMap.put(sum, new double[]{j, i});//optional: save this value can help to check the program
				temp.add(sum);
                j = i;
				sum = 0;
			}
			sum += number;
		}
        if(sum > 0){//handle the last sum which will not put into list
            temp.add(sum);
            tMap.put(sum, new double[]{j, i});
        }
		set.clear();
		set.addAll(0, temp);

		temp.clear();
		sum = 0;
		for (int i = 0; i < set.size() ; ++i) {
			number = set.get(i);
			if (number+sum < 0) {
				temp.add(sum);
                tMap.get()
				sum = 0;
			}else {
				sum += number;
                tMap.get()
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
	}
		
	
	public static void main (int argc, string[] argv) {
		new maxOfSum (new ArrayList(Arrays.asList(1, 2, 3, -4, 5, -6, -7, 8, 9))).parts();
}
