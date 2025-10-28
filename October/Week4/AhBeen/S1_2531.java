import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Map<Integer, Integer> map = new HashMap<>();
		for(int i = 0; i < k; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		int max = map.containsKey(c) ? map.size() : map.size() + 1;

		for(int i = 0; i < n; i++) {
			int l = arr[i];
			map.put(l, map.get(l) - 1);
			if(map.get(l) == 0) {
				map.remove(l);
			}
			int r = arr[(i + k) % n];
			map.put(r, map.getOrDefault(r, 0) + 1);

			int current = map.containsKey(c) ? map.size() : map.size() + 1;
			max = Math.max(max, current);
		}
		System.out.println(max);
	}
}