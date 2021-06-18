import java.util.Arrays;

public class Array {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double [] sourceArray = {1.1,2.2,3.3,4.4};
		double [] destinationArray = Arrays.copyOf(sourceArray, 6);
		System.out.println(Arrays.toString(destinationArray));
	}

}
