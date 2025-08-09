import java.util.*;
import java.io.*;

public class B2_10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[26];
        String s = br.readLine();

        for (int i = 0; i < 26; i++) {
            arr[i] = -1;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (arr[c - 97] == -1) {
                arr[c - 97] = i;
            }
        }

        for (int v : arr) {
            sb.append(v + " ");
        }

        System.out.println(sb);
    }
}

