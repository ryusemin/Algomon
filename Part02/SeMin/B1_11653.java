import java.io.*;
import java.util.*;

class B1_11653 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        while (n > 1) {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    list.add(i);
                    n /= i;
                    break;
                }
            }
        }

        for(int a : list){
            System.out.println(a);
        }

    }
}