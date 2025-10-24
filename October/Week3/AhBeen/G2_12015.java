import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] a = new int[n];
		int[] LIS = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		LIS[0] = a[0];
		int length = 1;
		for(int i = 0; i < n; i++) {
			int key = a[i];
			if(LIS[length - 1] < key) {
				length++;
				LIS[length - 1] = key;
			} else {
				int high = length;
				int low = 0;
				while(low < high)  {
					int mid = (low + high) / 2;
					if(LIS[mid] < key) {
						low = mid + 1;
					} else {
						high = mid;
					}
				}
				LIS[low] = key;
			}
		}
		System.out.println(length);
	}
}