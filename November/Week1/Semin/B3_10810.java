import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] NM = br.readLine().split(" "); 
        int N = Integer.parseInt(NM[0]); 
        int M = Integer.parseInt(NM[1]); 
        int[] bucket = new int[N+1]; 


        for(int q = 1; q <= M; q++) {
            String[] ijk =  br.readLine().split(" ");
            int i = Integer.parseInt(ijk[0]);
            int j = Integer.parseInt(ijk[1]);
            int k = Integer.parseInt(ijk[2]);

            for(int f = i; f <= j; f++) { 
                bucket[f] = k; 
            }
        }

        for(int i = 1; i <= bucket.length - 1; i++) {
            System.out.print(bucket[i] + " ");
        }
    }
}