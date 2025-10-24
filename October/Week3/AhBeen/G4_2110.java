import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] home = new int[n];

		for(int i = 0; i < n; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(home);

		int low = 1;
		int high = home[n - 1] - home[0] + 1;

		while(low < high) {
			int mid = (low + high) / 2;

			if(install(mid, home) < c) {
				high = mid;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(low - 1);
	}

	static int install(int distance, int[] home) {
		int count = 1;
		int locate = home[0];

		for(int i = 1; i < home.length; i++) {
			int l = home[i];
			if(l - locate >= distance) {
				count++;
				locate = l;
			}
		}
		return count;
	}
}