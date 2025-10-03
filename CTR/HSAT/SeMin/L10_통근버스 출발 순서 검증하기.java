import java.util.*;
import java.io.*;

public class Main
{

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] st = br.readLine().split(" ");
        int[] bus = new int[n+1];
        int[][] arr = new int[n+1][n+1];

        long answer = 0;
        for(int i = 0; i < n; i++){
            bus[i] = Integer.parseInt(st[i]);
        }

        for(int i= n - 2; i >=0; i--){
            for(int j = 1; j <= n; j++){
                if(bus[i+1] < j){
                    arr[j][i] = arr[j][i+1] + 1;
                }
                else{
                    arr[j][i] = arr[j][i+1];
                }
            }
        }

        for(int i = 0; i < n-2; i++){
            for(int j= i+1; j < n-1; j++){
                if(bus[i]<bus[j]){
                    answer += arr[bus[i]][j];
                }
            }
        }

        System.out.println(answer);

    }

}
