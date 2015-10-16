import java.lang.reflect.Array;
import java.util.Arrays;


public class synchedthings {

	/**
	 * @param args
	 */
	private final int[] array;
	private int currindex;
	
	public synchedthings(int size){
		array=new int[size];
	}
	public synchronized void add(int val){
		int position=currindex;
		array[position]=val;
		++currindex;
	}
	public boolean search(int val){
		Arrays.sort(array);
		int x= Arrays.binarySearch(array,val);
		if(x<0){//returns <0 if not in the array.
			return true;
		}
		else 
			return false;
	}
}
