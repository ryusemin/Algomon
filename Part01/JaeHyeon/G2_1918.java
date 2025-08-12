import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class G2_1918 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String str = br.readLine();
        for (char c : str.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                // 숫자나 문자 -> sb에 추가
                sb.append(c);
            } else if (c == '(') {
                // 여는 괄호 -> 스택에 추가
                stack.push(c);
            } else if (c == ')') {
                // 닫는 괄호 -> 여는 괄호를 만날 때까지 스택의 연산자를 모두 꺼내 sb에 추가
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && priority(c) <= priority(stack.peek())) {
                    // 연산자 우선 순위가 더 낮으면 -> sb에 stack.pop 추가 -> 이후 현재 연산자 스택에 추가
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            // 남은 연산자 모두 sb에 추가
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

    static int priority(char c) {
        if (c == '/' || c == '*') return 2;
        else if (c == '+' || c == '-') return 1;
        else return -1;
    }
}

