import java.util.*;
import java.io.*;

public class S4_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] s = br.readLine().split(" ");

            switch (s[0]) {
                case "push_front":
                    dq.addFirst(Integer.parseInt(s[1]));
                    break;
                case "push_back":
                    dq.addLast(Integer.parseInt(s[1]));
                    break;
                case "pop_front":
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.pollFirst()).append("\n");
                    }
                    break;
                case "pop_back":
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.pollLast()).append("\n");
                    }
                    break;
                case "size":
                    sb.append(dq.size()).append("\n");
                    break;
                case "empty":
                    if (dq.isEmpty()) {
                        sb.append("1\n");
                    } else {
                        sb.append("0\n");
                    }
                    break;
                case "front":
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.peekFirst()).append("\n");
                    }
                    break;
                case "back":
                    if (dq.isEmpty()) {
                        sb.append("-1\n");
                    } else {
                        sb.append(dq.peekLast()).append("\n");
                    }
                    break;
            }
        }
        System.out.println(sb);
    }
}
