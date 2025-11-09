import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        int count = 0;
        int start, end = -1;

        for (int i = 0; i < N ; i++) {
            String[] ar = br.readLine().split(" ");
            int s = Integer.parseInt(ar[0]);
            int e = Integer.parseInt(ar[1]);
            arr[i][0] = s;
            arr[i][1] = e;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        
        
        for (int i = 0; i < N ; i++) {
            int s = arr[i][0];
            int e = arr[i][1];
            if(end <= s){
                start = s;
                end = e;
                count++;
            }
            
        }
        System.out.println(count);
    }
}