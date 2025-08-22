import java.io.*;
import java.util.*;

public class B2_2745 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String n = st.nextToken();
        int b = Integer.parseInt(st.nextToken());

        int temp = 1;
        int result = 0;

        for (int i = n.length()-1; i >= 0; i--) {
            char c = n.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                result += (c - 55) * temp;
            } else {
                result += (c - 48) * temp;
            }
            temp *= b;
        }
        System.out.println(result);

    }
}

