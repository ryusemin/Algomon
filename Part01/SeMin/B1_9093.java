import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++){
            String[] arr = br.readLine().split(" ");

            for(int i = 0 ; i < arr.length ; i++){
                StringBuilder sb = new StringBuilder();
                sb.append(arr[i]);
                sb.reverse();
                System.out.print(sb.toString() + " ");
            }

            System.out.println();
        }
    }
}