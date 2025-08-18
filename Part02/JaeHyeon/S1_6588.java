import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S1_6588 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean[] arr = new boolean[1000001];
        Arrays.fill(arr, true);
        arr[0] = false;
        arr[1] = false;

        for (int i = 2; i <= Math.sqrt(1000000); i++) {
            if(!arr[i]) continue;
            for (int j = i * i; j <= 1000000; j += i) {
                arr[j] = false;
            }
        }

        int n;
        while ((n = Integer.parseInt(br.readLine())) != 0) {
            boolean isOk = false;
            for (int i = 3; i <= n; i += 2) {
                if (arr[i] && arr[n - i]) {
                    isOk = true;
                    sb.append(n).append(" = ").append(i).append(" + ").append(n - i).append("\n");
                    break;
                }
            }

            if (!isOk) {
                sb.append("Goldbach's conjecture is wrong.").append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}