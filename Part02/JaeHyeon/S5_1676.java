import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S5_1676 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int count5 = 0;
        for (int i = 2; i <= n; i++) {
            int temp = i;
            while (temp % 5 == 0) {
                count5++;
                temp /= 5;
            }
        }

        System.out.println(count5);
    }
}