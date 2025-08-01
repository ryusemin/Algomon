import java.util.*;
import java.io.*;

public class S4_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<Integer>();

        int a = 0;

        for (int i = 0; i < T; i++) {
            String[] s = br.readLine().split(" ");
            int size = queue.size();

            switch (s[0]) {
                case "push":
                    queue.add(Integer.parseInt(s[1]));
                    a = Integer.parseInt(s[1]);
                    break;
                case "pop":
                    if (size == 0) sb.append("-1\n");
                    else sb.append(queue.remove() + "\n");
                    break;
                case "size":
                    sb.append(size + "\n");
                    break;
                case "empty":
                    if (size == 0) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case "front":
                    if (size == 0) sb.append("-1\n");
                    else sb.append(queue.peek() + "\n");
                    break;
                case "back":
                    if (size == 0) sb.append("-1\n");
                    else sb.append(a + "\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
