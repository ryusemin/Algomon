import java.util.*;
import java.io.*;

public class B3_10872 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int res = 1;

        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        System.out.println(res);
    }
}
