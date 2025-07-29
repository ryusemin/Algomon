import java.util.*;
import java.io.*;

public class B1_9093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] words = br.readLine().split(" ");

            for (String word : words) {
                StringBuilder sb = new StringBuilder(word);
                System.out.print(sb.reverse().toString() + " ");
            }
            System.out.println();
        }
    }
}