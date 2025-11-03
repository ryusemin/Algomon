import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] a = new int[n];

		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(a);

		int i = 0;
		int j = 0;
		int num = Integer.MAX_VALUE;

		while(i < n) {
			if(a[i] - a[j] < m) {
				i++;
				continue;
			} else if(a[i]- a[j] == m) {
				num = m;
				break;
			} else {
				num = Math.min(num, a[i] - a[j]);
				j++;
			}
		}
		System.out.println(num);
	}
}