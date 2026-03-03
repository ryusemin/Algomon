import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] arr = br.readLine().split("");

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i].equals("P")) {
                int startIndex = Math.max(i - K, 0);
                int endIndex = Math.min(i + K, N - 1);
                for (int j = startIndex; j <= endIndex; j++) {
                    if (arr[j].equals("H")) {
                        count++;
                        arr[j] = "X";
                        break;
                    }
                }
            }

        }
        System.out.println(count);
    }
}