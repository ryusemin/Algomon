import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long B = sc.nextLong();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}

		Arrays.sort(a);

		long low = a[0];
		long high = a[n - 1] + (long) Math.sqrt(B);

		long ans = 0;

		while(low <= high) {
			long mid = (low + high) / 2;

			if(calc(mid, a, B)) {
				low = mid + 1;
				ans = mid;
			} else {
				high = mid - 1;
			}
		}

		System.out.println(ans);
	}

	static boolean calc(long mid, int[] a, long B) {
		long total = 0;

		for(int num : a) {
			if (num >= mid) break;

			total += Math.pow(mid - num, 2);

			if(total > B) return false;
		}

		if(total > B) {
			return false;
		} else {
			return true;
		}
	}
}
