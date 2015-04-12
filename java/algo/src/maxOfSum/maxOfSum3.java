package maxOfSum;

/**
 * author:zzt
 * date:2014.8.13, 9.28
 * description: find the maximum of sum of a successive subset in a set; eg.{21, -9, -4, 28, -29, 45, 83}
 * version:0.3 -- collapse algorithm -- can also return the index of begin and end
 */
import java.util.*;

public class maxOfSum3 {
	ArrayList<Double> set ;

	public maxOfSum3 (ArrayList<Double> set) {
		this.set = set;
	}

	public double parts () {
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
            //enter the following two blocks when the sign of a number is changed
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
		sum = set.get(0);
		for (i = 1; i+1 < set.size() ; i+=2) {
			number = set.get(i);
			if (number+sum<0 || number+set.get(i+1)<=0) {
				temp.add(sum);
				sum = set.get(i+1);
			}else {
				sum = number + set.get(i+1) + sum;
                if(tMap.get(sum)[1] == tMap.get(number)[0] && tMap.get(set.get(i+1))[0] == tMap.get(number)[1]){
                    tMap.put(sum, new double[]{tMap.get(sum)[0], tMap.get(set.get(i+1))[1]});
                }else{
                    System.err.println("wrong in concatenation");
                }
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
		System.out.println(max + ": from " + tMap.get(max)[0] + " to " + tMap.get(max)[0]);
        return max;
	}
		
	public static void main (String[] argv) {
		new maxOfSum (new ArrayList<Double>(Arrays.asList(1.0, 2.0, 3.0, -4.0, 7.0, -6.0, -7.0, 8.0))).parts();
    }
}
