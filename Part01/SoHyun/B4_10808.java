import java.util.*;
import java.io.*;

public class B4_10808 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[26];
        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 97]++;
        }

        for (int i = 0; i < 26; i++) {
            sb.append(arr[i] + " ");
        }

        System.out.println(sb);
    }
}
