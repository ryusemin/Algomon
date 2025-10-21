import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		PriorityQueue<Integer> p_pq = new PriorityQueue<>(Comparator.reverseOrder());
		PriorityQueue<Integer> m_pq = new PriorityQueue<>();
		int sum = 0;

		for(int i = 0; i < n; i++) {
			int num = sc.nextInt();
			if(num <= 0) {
				m_pq.add(num);
			} else if(num == 1) {
				sum++;
			} else {
				p_pq.add(num);
			}
		}

		while(m_pq.size() > 1) {
			int a = m_pq.poll();
			int b = m_pq.poll();

			sum += a * b;
		}
		if(!m_pq.isEmpty()) {
			if(m_pq.peek() == 0) {
				m_pq.poll();
			} else {
				sum += m_pq.poll();
			}
		}
		while(p_pq.size() > 1) {
			int a = p_pq.poll();
			int b = p_pq.poll();

			sum += a * b;
		}
		if (!p_pq.isEmpty()) sum += p_pq.poll();
		System.out.println(sum);
	}
}