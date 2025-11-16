import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()) - 1;
		int num = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i2 - i1);
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}

		int count = 0;
		while (!pq.isEmpty() && pq.peek() >= num) {		
			num++;
			pq.add(pq.poll() - 1);
			count++;
		}

		System.out.println(count);
	}
}