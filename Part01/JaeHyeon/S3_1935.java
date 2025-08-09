import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class S3_1935 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();

        Double[] arr = new Double[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        Stack<Double> stack = new Stack<>();

        double num1, num2;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '+':
                    stack.push(stack.pop() + stack.pop());
                    break;
                case '-':
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 - num1);
                    break;
                case '/':
                    num1 = stack.pop();
                    num2 = stack.pop();
                    stack.push(num2 / num1);
                    break;
                case '*':
                    stack.push(stack.pop() * stack.pop());
                    break;
                default:
                    stack.push(arr[ch - 65]);
            }
        }

        System.out.printf("%.2f", stack.pop());

    }
}
