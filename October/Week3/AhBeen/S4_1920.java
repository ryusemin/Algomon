import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int low = 0;
		int high = sc.nextInt() - 1;

		int[] arr = new int[high + 1];
		for(int i = 0; i < high + 1; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		int m = sc.nextInt();
		for(int i = 0; i < m; i++) {
			int key = sc.nextInt();
			int result = binarySearch(key, low, high, arr);
			System.out.println(result == -1 ? "0" : "1");
		}
	}
	static int binarySearch(int key, int low, int high, int[] arr) {
		int mid;
		while(low <= high) {
			mid = (low + high) / 2;
			if(key == arr[mid]) {
				return mid;
			} else if(key < arr[mid]) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}
}