import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class S5_11576 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<Integer> deque = new ArrayDeque<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        int[] nums = new int[m];

        int sum = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = m - 1; i >= 0; i--) {
            sum += Integer.parseInt(st.nextToken()) * (int) Math.pow(A, i);
        }

        while (sum != 0) {
            int remainder = sum % B;
            deque.add(remainder);
            sum /= B;
        }

        while (!deque.isEmpty()) {
            sb.append(deque.pollLast()).append(" ");
        }

        System.out.println(sb.toString());
    }
}
