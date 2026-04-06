import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		if(K >= N) {
			System.out.println(0);
			System.exit(0);
		}

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] sensors = new int[N];
		for(int i = 0; i < N; i++) {
			sensors[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sensors);

		Integer[] dist = new Integer[sensors.length - 1];

		for(int i = 0 ; i < N - 1; i++) {
			dist[i] = sensors[i + 1] - sensors[i];
		}

		Arrays.sort(dist, Comparator.reverseOrder());

		int answer = 0;

		for(int i =  K - 1; i < dist.length; i++) {
			answer += dist[i];
		}

		System.out.println(answer);
	}
}