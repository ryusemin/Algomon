import java.util.*;
import java.io.*;

public class S4_11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String[] arr = new String[s.length()];

        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.substring(i, s.length());
        }

        Arrays.sort(arr);

        for (String a : arr) {
            System.out.println(a);
        }
    }
}
