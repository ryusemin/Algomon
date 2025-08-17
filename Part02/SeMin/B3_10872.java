import java.io.*;
import java.util.*;

class B3_10872 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long result = 1;

        for(int i = 1; i<= n; i++){
            result *= i;
        }
        System.out.println(result);
    }

}