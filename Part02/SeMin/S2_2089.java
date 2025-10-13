import java.io.*;
import java.util.*;

class S2_2089 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.println(0);
            return;
        }

        while (n != 0) {
            int remainder = n % -2;
            n /= -2;

            if (remainder < 0) {
                remainder += 2;
                n += 1;
            }
            sb.append(remainder);
        }

        System.out.println(sb.reverse().toString());
    }
}