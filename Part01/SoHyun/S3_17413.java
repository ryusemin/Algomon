import java.util.*;
import java.io.*;

public class S3_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        Stack<Character> stack = new Stack<>();

        boolean flag = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '<') {
                flag = true;
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(c);
            }

            else if (c == '>') {
                flag = false;
                sb.append(c);
            }

            else if (flag) {
                sb.append(c);
            }

            else if (!flag) {
                if (c == ' ') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(c);
                } else {
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }
}
