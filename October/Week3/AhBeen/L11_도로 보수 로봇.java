import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] positions = new int[N];
		for (int i = 0; i < N; i++) {
			positions[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(positions);
		int low = 1;
		int high = positions[N - 1] - positions[0] + 1;
		int result = high;

		while(low <= high){
			int mid = (low + high) / 2;

			if(isPossible(mid, positions, N, K)){
				result = mid;
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		System.out.println(result);
	}

	static boolean isPossible(int l, int[] positions, int N, int K) {
		int c = 1;
		int last = positions[0];

		for(int i = 1; i < N; i++) {
			if(positions[i] - last + 1 > l) {
				c++;
				last = positions[i];
			}
		}
		return c <= K;
	}
}