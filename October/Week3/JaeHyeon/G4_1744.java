import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static int n;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> plus = new PriorityQueue<>((i1, i2) -> {
            return i2 - i1;
        });

        PriorityQueue<Integer> minus = new PriorityQueue<>((i1, i2) -> {
            return i1 - i2;
        });

        boolean existZero = false;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                plus.add(num);
            } else if (num < 0) {
                minus.add(num);
            } else {
                existZero = true;
            }
        }

        int sum = 0;
        while (!plus.isEmpty()) {
            if (plus.size() >= 2) {
                int a = plus.poll();
                int b = plus.poll();
                if (a == 1 || b == 1) {
                    sum += a + b;
                } else {
                    sum += a * b;
                }
            } else {
                sum += plus.poll();
            }
        }

        while (!minus.isEmpty()) {
            if (minus.size() >= 2) {
                int a = minus.poll();
                int b = minus.poll();
                sum += a * b;
            } else {
                int a = minus.poll();
                if (!existZero) {
                    sum += a;
                }
            }
        }

        System.out.println(sum);
    }
}