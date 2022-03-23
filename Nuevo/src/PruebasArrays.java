
public class PruebasArrays {

	public static void main(String[] args) {
		// Encontrar valor máximo de un array
		int array[] = {1,23,14,205,48};
		int ind = 0;
		for (int i = 1; i < array.length; i++){
			if (array[i] > array[ind]){
				ind = i;
			}
		}
		System.out.println(ind);
	}

}
