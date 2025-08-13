import java.util.*;
import java.io.*;

public class B2_10820 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        int[] arr;

        while ((s = br.readLine()) != null) {
            arr = new int[4];

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (c >= 'A' && c <= 'Z') {
                    arr[1]++;
                }
                else if (c >= 'a' && c <= 'z') {
                    arr[0]++;
                }
                else if (c == ' ') {
                    arr[3]++;
                }
                else {
                    arr[2]++;
                }
            }

            for (int a : arr) {
                sb.append(a + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}
