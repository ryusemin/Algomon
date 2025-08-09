import java.util.*;
import java.io.*;

class S2_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = 1;

        List<String> printArr = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();


        for(int tc = 0; tc < n; tc++){
            int number = Integer.parseInt(br.readLine());

            boolean matched = false;

            if(!stack.isEmpty() && stack.peek() == number) {
                stack.pop();
                printArr.add("-");
                continue;
            }

            while (k <= n) {
                stack.push(k++);
                printArr.add("+");

                if(stack.peek() == number) {
                    stack.pop();
                    printArr.add("-");
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                System.out.println("NO");
                return;
            }
        }

        for (String s : printArr) {
            System.out.println(s);
        }
    }
}