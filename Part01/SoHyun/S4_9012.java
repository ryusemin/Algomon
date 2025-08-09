import java.util.*;
import java.io.*;

public class S4_9012 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String s = br.readLine();
            Stack<Character> stack = new Stack<>();

            boolean isValid = true;

            for (int j = 0; j < s.length(); j++) {
                char c = s.charAt(j);

                if (c == '(') {
                    stack.push(c);
                }
                else if (stack.empty()) {
                    isValid = false;
                }
                else {
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


