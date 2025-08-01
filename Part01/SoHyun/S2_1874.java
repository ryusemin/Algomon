import java.util.*;
import java.io.*;

public class S2_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        int last_value = 0;

        for (int i = 0; i < T; i++) {
            int value = Integer.parseInt(br.readLine());

            if (value > last_value) {
                for (int j = last_value+1; j <= value; j++) {
                    stack.push(j);
                    sb.append("+").append("\n");
                }
                last_value = value;
            } else {
                if (stack.peek() != value) {
                    System.out.println("NO");
                    return;
                }
            }

            stack.pop();
            sb.append("-").append("\n");
        }
        System.out.println(sb);
    }

}
