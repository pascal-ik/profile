package arrayManipulation;

import java.util.ArrayList;
import java.util.List;

public class sortAlgorithms {

	/**
	 * sorts an array in ascending order
	 * @param arr
	 */
	public void sortAscending(int [] arr) {
		int temp = 0;
		int n = arr.length;
		String result = "";
		for (int i = 0; i < n; i++) 
        {
            for (int j = i + 1; j < n; j++) 
            {
                if (arr[i] > arr[j]) 
                {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
		
		for(int item: arr) {
			result += item + ", ";
		}
		
		System.out.println(result);
	}
	
	/**
	 * Prints out all the ordered substrings of a string
	 * @param sub
	 */
	public List<String> sortSubs(String sub) {
		List<String> result = new ArrayList<>();
        for (int length = sub.length(); length > 0; length--) {
        	
            for (int start = 0; start <= (sub.length()-length); start++) {
                System.out.println(sub.substring(start, start+length));
                result.add(sub.substring(start, start+length));
            }
        }
        return result;
	}

	
}
