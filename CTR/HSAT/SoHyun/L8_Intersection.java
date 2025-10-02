import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        Arrays.fill(arr, -1);

        Queue<int[]>[] q = new Queue[4];


        for (int i = 0; i < 4; i++) {
            q[i] = new ArrayDeque<int[]>();
        }

        for (int i = 0; i < n; i++) {
            String[] tw = br.readLine().split(" ");
            int[] ip = new int[]{Integer.parseInt(tw[0]), i};
            int dir = tw[1].charAt(0) - 'A';
            q[dir].add(ip);
        }

        int t = 0;
        boolean[] rotary = new boolean[4];

        while (!q[0].isEmpty() || !q[1].isEmpty() || !q[2].isEmpty() || !q[3].isEmpty()) {
            int minT = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                rotary[i] = (!q[i].isEmpty() && q[i].peek()[0] <= t);
                if (!q[i].isEmpty()) minT = Math.min(minT, q[i].peek()[0]);
            }

            if (rotary[0] && rotary[1] && rotary[2] && rotary[3])
                break;
            else if (rotary[0] == false && rotary[1] == false && rotary[2] == false && rotary[3] == false) {
                t = minT;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if (rotary[i] && !rotary[(i+3) % 4]) {
                    int o = q[i].poll()[1];
                    arr[o] = t;
                }
            }

            t++;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}