import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if (n == 1 || (n == 2 && arr[0] != arr[1])) {
            System.out.println("A");
        } else if (n == 2) {
            System.out.println(arr[0] + "");
        } else {
            int a, b;
            if (arr[1] == arr[0]) {
                a = 1;
                b = 0;
            } else {
                a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
                b = arr[1] - (arr[0] * a);
            }

            int i = 1;
            for (; i < n; i++) {
                if (arr[i] != (arr[i - 1] * a + b))
                    break;
            }
            if (i != n) {
                System.out.println("B");
            } else {
                System.out.println((arr[n - 1] * a + b) + "");
            }
        }
    }
}