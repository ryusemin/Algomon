import java.util.*;

public class Main{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] coins = new int[n];
		int count = 0;

		for(int i = 0; i < n; i++) {
			coins[i] = sc.nextInt();
		}
		for(int i = n - 1; i >= 0; i--) {
			if(k >= coins[i]) {
				int num = k / coins[i];
				count +=  num;
				k -= coins[i] * num;
			}
		}
		System.out.println(count);
	}
}