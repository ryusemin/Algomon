import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int i = 0;
		int j = 0;
		int length = Integer.MAX_VALUE;
		int sum = 0;
		while(i < n) {
			if(sum >= s) {
				length = Math.min(length, j - i);
				sum = sum - arr[i];
				i++;
			} else if(j == n) {
				break;
			} else {
				sum += arr[j];
				j++;
			}
		}
		System.out.println(length == Integer.MAX_VALUE ? 0 : length);
	}
}