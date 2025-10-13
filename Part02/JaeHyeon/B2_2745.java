import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_2745 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        int sum = 0;
        for (int i = N.length() - 1; i >= 0; i--) {
            int num = N.charAt(i);
            if (num >= 65 && num <= 90) {
                sum += (num - 55) * (int) Math.pow(B, N.length() - i - 1);
            } else {
                sum += (num - 48) * (int) Math.pow(B, N.length() - i - 1);
            }
        }
        System.out.println(sum);
    }
}
