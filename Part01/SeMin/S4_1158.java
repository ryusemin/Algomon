import java.util.*;
import java.io.*;

public class S4_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] arr = br.readLine().split(" ");

        int N = Integer.parseInt(arr[0]);
        int K = Integer.parseInt(arr[1]);

        Integer[] ar = new Integer[N];
        for(int i = 1; i <=N; i++){
            ar[i-1] = i;
        }

        Queue<Integer> deque = new ArrayDeque<>(Arrays.asList(ar));

        System.out.print("<");

        int num = 1;

        while(deque.size() > 1){
            int a = deque.poll();
            if(num % K == 0){
                System.out.print(a + ", ");
            }
            else{
                deque.offer(a);
            }
            num++;
        }
        System.out.print( deque.poll()+ ">");


    }
}