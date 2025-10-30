import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, m;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);

        int en = 0;
        int min = Integer.MAX_VALUE;
        for (int st = 0; st < n; st++) {
            while (en < n && nums[en] - nums[st] < m) {
                en++;
            }

            if (en == n) {
                break;
            }
            min = Math.min(min, nums[en] - nums[st]);
        }

        System.out.println(min);
    }
}