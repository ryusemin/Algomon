import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int leafCount = (int) Math.pow(2, H);
        int node = (int) Math.pow(2, H + 1);

        Queue<Integer>[] left = new LinkedList[node];
        Queue<Integer>[] right = new LinkedList[node];

        for (int i = 0; i < node; i++) {
            left[i] = new LinkedList<>();
            right[i] = new LinkedList<>();
        }

        for (int i = 0; i < leafCount; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < K; j++) {
                int num = Integer.parseInt(st.nextToken());
                left[leafCount + i].add(num);
            }
        }

        int res = 0;

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j < node; j++) {
                Queue<Integer> q = (i % 2 == 1 || j >= leafCount) ? left[j] : right[j];
                
                if (q.isEmpty()) continue;

                int work = q.poll();
                if (j == 1) {
                    res += work;
                } else {
                    int parent = j / 2;
                    if (j % 2 == 0) left[parent].add(work);
                    else right[parent].add(work);
                }
            }
        }

        System.out.println(res);
    }
}