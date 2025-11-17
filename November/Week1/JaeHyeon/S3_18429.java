import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k, cnt;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cnt = 0;

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] used = new boolean[n];
        dfs(nums, used, 0, 500);

        System.out.println(cnt);
    }

    static void dfs(int[] nums, boolean[] used, int day, int weight) {
        if (day == n) {
            cnt++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (used[i]) continue;

            int next = weight + nums[i] - k;
            if (next < 500) continue;

            used[i] = true;
            dfs(nums, used, day + 1, next);
            used[i] = false;
        }
    }
}
