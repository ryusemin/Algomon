import java.util.*;
import java.io.*;

public class Main {
    static int[] arr, rps;
    static int n;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        rps = new int[3];
        for(int i = 0; i < 3; i++){
            rps[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(1, arr[0]);
        System.out.println(min + " " + max);
    }
    static void backtracking(int depth, int sum){
        if(depth == n){
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        int num = arr[depth];
        for(int i = 0; i < 3; i++){
            if(rps[i] > 0) {
                rps[i]--;

                switch(i){
                    case 0:
                        backtracking(depth + 1, sum + num);
                        break;
                    case 1:
                        backtracking(depth + 1, sum - num);
                        break;
                    case 2:
                        backtracking(depth + 1, sum * num);
                        break;
                }

                rps[i]++;
            }
        }
    }
}