import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i< N ;i++) {
            String[] ar = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(ar[0]);
            arr[i][1] = Integer.parseInt(ar[1]);
        }

        Arrays.sort(arr, (int[] o1, int[] o2) ->{
                   if(o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
            }
        );

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(arr[0][1]);
        
        for (int i = 1; i< N ; i++) {
            if(pq.peek() <= arr[i][0] ) pq.poll();
            pq.add(arr[i][1]);
        }
        
        System.out.println(pq.size());
    }
}