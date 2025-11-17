import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static int n;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> i1 - i2);
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        int sum = 0;

        while (pq.size() >= 2) {
            int first = pq.poll();
            int second = pq.poll();

            sum += first + second;
            pq.add(first + second);
        }

        System.out.println(sum);
    }
}
