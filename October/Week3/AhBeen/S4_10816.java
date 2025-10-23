import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int low = 0;

		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < m; i++) {
			int key = Integer.parseInt(st.nextToken());
			int count = upperBound(arr, key) - lowerBound(arr, key);
			sb.append(count).append(" ");
		}

		System.out.println(sb);
	}
	static int lowerBound(int[] arr, int key) {
		int low = 0;
		int high = arr.length;

		while (low < high) {
			int mid = (low + high) / 2;
			if (arr[mid] >= key) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	static int upperBound(int[] arr, int key) {
		int low = 0;
		int high = arr.length;

		while (low < high) {
			int mid = (low + high) / 2;
			if (arr[mid] > key) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}
}