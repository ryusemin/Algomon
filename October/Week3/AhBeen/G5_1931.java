import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] part = new int[n][2];
		for(int i = 0; i < n; i++) {
			part[i][0] = sc.nextInt();
			part[i][1] = sc.nextInt();
		}

		Arrays.sort(part, new Comparator<int[]>(){
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});

		int count = 0;
		int prev = 0;
		for(int i = 0; i < n; i++) {
			if(prev <= part[i][0]) {
				prev = part[i][1];
				count++;
			}
		}

		System.out.println(count);
	}
}