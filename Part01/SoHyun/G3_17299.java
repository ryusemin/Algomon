import java.util.*;
import java.io.*;

public class G3_17299 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] index = new int[n];
        int[] count = new int[1000001];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            index[i] = Integer.parseInt(st.nextToken());
            count[index[i]]++;
        }

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && count[index[i]] > count[index[stack.peek()]]) {
                arr[stack.pop()] = index[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            arr[stack.pop()] = -1;
        }

        for (int i = 0; i < n; i++) {
            sb.append(arr[i] + " ");
        }

        System.out.println(sb);
    }
}

