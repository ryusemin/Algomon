import java.util.*;
import java.io.*;

class B3_10824 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] st = br.readLine().split(" ");

        long sum = 0;
        long part = 0;

        for(int i = 0; i< 4; i++){
            int n = Integer.parseInt(st[i]);

            if(i % 2 == 0) {
                part = n * (long)Math.pow(10, st[i+1].length());
            }
            else{
                part += n;
                sum += part;
                part = 0;
            }
        }

        System.out.println(sum);



    }
}