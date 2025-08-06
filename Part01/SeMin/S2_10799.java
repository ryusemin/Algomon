import java.util.*;
import java.io.*;

public class S2_10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String stick = br.readLine();

        int answer = 0;
        boolean flag = false;

        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < stick.length(); i++) {
            char n = stick.charAt(i);

            if (n == ')') {
                if (!deque.isEmpty() && deque.peek() == '(') {
                    if (flag) {
                        deque.pop();
                        answer += deque.size();
                        flag = false;
                    }
                    else {
                        deque.pop();
                        answer++;
                        flag = false;
                    }
                }
            }
            else {
                deque.push(n);
                flag = true;
            }

        }
        System.out.println(answer);
    }
}