import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for(int t = 0; t < T; t++) {
			int k = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();

			for(int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char op = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());

				if(op == 'I') {
					map.put(num, map.getOrDefault(num, 0) + 1);
				} else if (op == 'D') {
					if(map.isEmpty()) continue;

					int key = (num == 1) ? map.lastKey() : map.firstKey();
					int count = map.get(key);
					if(count == 1) {
						map.remove(key);
					} else {
						map.put(key, count - 1);
					}
				}
			}
			if(map.isEmpty()) {
				sb.append("EMPTY\n");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}

		System.out.print(sb);
	}
}