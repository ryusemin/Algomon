import java.util.*;
import java.io.*;

public class S4_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        sb.append("<");

        while (q.size() > 1) {
            for (int i = 0; i < K-1; i++) {
                q.offer(q.poll());
            }
            sb.append(q.poll() + ", ");
        }

        sb.append(q.poll() + ">");

        System.out.println(sb.toString());
    }
}
