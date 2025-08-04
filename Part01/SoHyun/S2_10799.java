import java.util.*;
import java.io.*;

public class S2_10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] pipes = br.readLine().toCharArray();

        Stack<Character> stack = new Stack<>();

        int count = 0;

        for (int i = 0; i < pipes.length; i++) {
            if (pipes[i] == '(') {
                stack.push(pipes[i]);
                count++;
            }
            else if (pipes[i] == ')') {
                if (pipes[i-1] == '(') {
                    stack.pop();
                    count--;

                    count = count + stack.size();
                    continue;
                }
                else {
                    stack.pop();
                }
            }
        }
        System.out.println(count);
    }
}
