import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_11005 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        while (N != 0) {
            int remainder = N % B;
            if (remainder < 10) {
                sb.append(remainder);
            } else {
                remainder += 55;
                sb.append((char) remainder);
            }
            N /= B;
        }

        System.out.println(sb.reverse().toString());
    }
}
