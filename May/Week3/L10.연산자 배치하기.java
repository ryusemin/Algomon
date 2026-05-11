import java.util.*;
import java.io.*;

public class Main {
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] nums = new int[n];
		int[] op = new int[3];

		StringTokenizer st = new StringTokenizer(br.readLine());;

		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 3; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		dfs(nums, op, 1, nums[0]);

		System.out.print(min + " " + max);
	}

	static void dfs(int[] nums, int[] op, int depth, int sum) {
		if(depth == nums.length) {
			max = Math.max(sum, max);
			min = Math.min(sum, min);
			return;
		}

		for(int i = 0; i < 3; i++) {
			if(op[i] <= 0) continue;

			op[i]--;
			if(i == 0) {
				dfs(nums, op, depth + 1, sum + nums[depth]);
			} else if(i == 1) {
				dfs(nums, op, depth + 1, sum - nums[depth]);
			} else if(i == 2) {
				dfs(nums, op, depth + 1, sum * nums[depth]);
			}
			op[i]++;
		}
	}
}