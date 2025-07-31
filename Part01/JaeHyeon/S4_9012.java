import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class S4_9012 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            Stack<Character> stack = new Stack<>();
            String str = br.readLine();
            boolean isOk = true;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch == '(') {
                    stack.push(ch);
                } else {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        isOk = false;
                        break;
                    } else {
                        stack.pop();
                    }
                }
            }

            if (!stack.isEmpty()) {
                isOk = false;
            }

            sb.append(isOk ? "YES" : "NO").append("\n");
        }

        System.out.println(sb.toString());
    }
}