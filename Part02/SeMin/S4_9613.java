import java.io.*;
import java.util.*;

class S4_9613 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            long sum = 0;
            int n = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];

            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    sum += getGCD(arr[j], arr[k]);
                }
            }
            sb.append(sum).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int getGCD(int num1, int num2) {
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        while (num2 != 0) {
            int r = num1 % num2;
            num1 = num2;
            num2 = r;
        }

        return num1;
    }
}