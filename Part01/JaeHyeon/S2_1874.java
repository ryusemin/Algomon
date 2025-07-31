import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class S2_1874 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());

        int idx = 1;
        for (int t = 0; t < N; t++) {
            int num = Integer.parseInt(br.readLine());

            while (idx <= num) {
                stack.push(idx++);
                sb.append("+").append("\n");
            }

            if (stack.peek() == num) {
                stack.pop();
                sb.append("-").append("\n");
            } else {
                System.out.println("NO");
                return;
            }
        }

        System.out.println(sb.toString());

    }
}