import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int da = Integer.parseInt(br.readLine());

		PriorityQueue <Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		for(int i = 0; i < N - 1; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		int votes = 0;
		while(!pq.isEmpty() && pq.peek() >= da) {
			da++;
			pq.add(pq.poll() - 1);
			votes++;
		}
		System.out.println(votes);
	}
}