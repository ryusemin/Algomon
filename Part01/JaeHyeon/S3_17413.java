import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class S3_17413 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Deque<Character> deque = new ArrayDeque<>();

        String str = br.readLine();
        boolean isTag = false;

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == '<') {
                while (!deque.isEmpty()) {
                    sb.append(deque.pollLast());
                }
                isTag = true;
                sb.append('<');
            } else if (ch == '>') {
                isTag = false;
                sb.append('>');
            } else if (isTag) {
                sb.append(ch);
            } else {
                if (ch == ' ') {
                    while (!deque.isEmpty()) {
                        sb.append(deque.pollLast());
                    }
                    sb.append(" ");
                } else {
                    deque.add(ch);
                }
            }
        }

        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }

        System.out.println(sb.toString());
    }
}