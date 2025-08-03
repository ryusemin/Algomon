import java.util.*;
import java.io.*;

public class S4_10828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < T; i++) {
            String[] s = br.readLine().split(" ");
            int size = stack.size();

            switch (s[0]) {
                case "push":
                    stack.push(Integer.parseInt(s[1]));
                    break;
                case "pop":
                    if (size == 0) {
                        System.out.println("-1");
                        break;
                    }
                    System.out.println(stack.pop());
                    break;
                case "size":
                    System.out.println(stack.size());
                    break;
                case "empty":
                    System.out.println(size == 0 ? 1 : 0);
                    break;
                case "top":
                    if (size == 0) {
                        System.out.println("-1");
                        break;
                    }
                    System.out.println(stack.get(stack.size()-1));
                    break;
            }

        }

    }
}