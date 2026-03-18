import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int truthCount = Integer.parseInt(st.nextToken());

		boolean[] knowTruth = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();

		for (int i = 0; i < truthCount; i++) {
			int person = Integer.parseInt(st.nextToken());
			knowTruth[person] = true;
			q.add(person);
		}

		List<Integer>[] partyPeople = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			partyPeople[i] = new ArrayList<>();
		}

		List<Integer>[] personParties = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			personParties[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			for (int j = 0; j < cnt; j++) {
				int person = Integer.parseInt(st.nextToken());
				partyPeople[i].add(person);
				personParties[person].add(i);
			}
		}

		boolean[] visitedParty = new boolean[M];

		while (!q.isEmpty()) {
			int person = q.poll();

			for (int party : personParties[person]) {
				if (visitedParty[party]) continue;
				visitedParty[party] = true;

				for (int nextPerson : partyPeople[party]) {
					if (!knowTruth[nextPerson]) {
						knowTruth[nextPerson] = true;
						q.add(nextPerson);
					}
				}
			}
		}

		int answer = 0;

		for (int i = 0; i < M; i++) {
			boolean canLie = true;

			for (int person : partyPeople[i]) {
				if (knowTruth[person]) {
					canLie = false;
					break;
				}
			}

			if (canLie) answer++;
		}

		System.out.println(answer);
	}
}