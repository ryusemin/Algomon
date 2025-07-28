import java.util.Scanner;
import java.util.Stack;

public class S4_9012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            String s = sc.next();
            Stack<Character> stack = new Stack<>();

            boolean isValid = true;

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);

                if (c == '(') {
                    stack.push(c);
                } else if (stack.empty()) {
                    isValid = false;
                } else {
                    stack.pop();
                }
            }

            if (!stack.empty()) {
                isValid = false;
            }

            System.out.println(isValid ? "YES" : "NO");
        }
    }
}


