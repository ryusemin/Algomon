
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	static class Meeting {
		int start;
		int end;
		Meeting(int start, int end) {
			this.end = end;
			this.start = start;
		}
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Meeting[] meetings = new Meeting[N];
		
		for (int i = 0; i < N; i++) {
			meetings[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		
		Arrays.sort(meetings, new Comparator<Meeting>() {

			@Override
			public int compare(Meeting o1, Meeting o2) {
				if (o1.end == o2.end) {
					return o1.start - o2.start;
				}
				return o1.end - o2.end;
			}
		});
		
		int cnt = 0;
		int ended = 0;
		for (int i = 0; i < N; i++) {
			if (ended <= meetings[i].start) {
				cnt++;
				ended = meetings[i].end;
			}
		}
		
		System.out.println(cnt);
	}

}
