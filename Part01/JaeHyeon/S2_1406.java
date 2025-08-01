import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class S2_1406 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        String start = br.readLine();
        int N = Integer.parseInt(br.readLine());

        for (char c : start.toCharArray()) {
            stack1.push(c);
        }

        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            char cmd = st.nextToken().charAt(0);
            switch (cmd) {
                case 'L':
                    if (!stack1.isEmpty()) stack2.push(stack1.pop());
                    break;
                case 'D':
                    if (!stack2.isEmpty()) stack1.push(stack2.pop());
                    break;
                case 'B':
                    if (!stack1.isEmpty()) stack1.pop();
                    break;
                case 'P':
                    stack1.push(st.nextToken().charAt(0));
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            sb.append(stack2.pop());
        }

        System.out.println(sb.toString());
    }
}