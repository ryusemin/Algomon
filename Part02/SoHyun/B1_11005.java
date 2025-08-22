import java.io.*;
import java.util.*;

public class B1_11005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        String result = "";

        while (n > 0) {
            int temp = n % b;
            if (temp >= 10) {
                char c = (char) (temp + 55);
                result += c;
            } else {
                result += temp;
            }
            n /= b;
        }

        for (int i = result.length()-1; i >= 0; i--) {
            System.out.print(result.charAt(i));
        }

    }
}

