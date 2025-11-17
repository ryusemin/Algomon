import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, l, r, x, cnt;
    static int[] nums;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        cnt = 0;

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        dfs(0, 0, 0, 0, 0);

        System.out.println(cnt);
    }

    static void dfs(int idx, int picked, int sum, int min, int max) {
        if (idx == n) {
            if (picked >= 2 && sum >= l && sum <= r && (max - min) >= x) {
                cnt++;
            }
            return;
        }

        int nextMin = (picked == 0) ? nums[idx] : Math.min(min, nums[idx]);
        int nextMax = (picked == 0) ? nums[idx] : Math.max(max, nums[idx]);
        dfs(idx + 1, picked + 1, sum + nums[idx], nextMin, nextMax);

        dfs(idx + 1, picked, sum, min, max);
    }

}
