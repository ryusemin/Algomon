import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B3_10872 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.println(1);
            return;
        }

        int sum = 1;
        for (int i = 1; i <= n; i++) {
            sum *= i;
        }

        System.out.println(sum);
    }
}