import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, c;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i = 1; i < n; i++) {
            int num = nums[i];

            if (list.get(list.size() - 1) < num) {
                list.add(num);
            } else {
                int lo = 0;
                int hi = list.size() - 1;

                while (lo < hi) {
                    int mid = (lo + hi) / 2;
                    if (list.get(mid) >= num) {
                        hi = mid;
                    } else {
                        lo = mid + 1;
                    }
                }
                list.set(hi, num);
            }
        }

        System.out.println(list.size());
    }
}