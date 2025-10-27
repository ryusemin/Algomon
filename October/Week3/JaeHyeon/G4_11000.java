import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Class[] classes = new Class[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            classes[i] = new Class(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(classes, (c1, c2) -> {
            return c1.start == c2.start ? c1.end - c2.end : c1.start - c2.start;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int sT = classes[i].start;
            int eT = classes[i].end;

            if (!pq.isEmpty() && pq.peek() <= sT) {
                pq.poll();
            }

            pq.add(eT);
        }

        System.out.println(pq.size());
    }
}

class Class {
    int start, end;
    Class(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
