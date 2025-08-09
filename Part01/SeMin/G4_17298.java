import java.util.*;
import java.io.*;

class G4_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] ss = br.readLine().split(" ");
        int[] arr = new int[ss.length];

        Deque<Integer> stack = new ArrayDeque<>();

        for(int i =0; i< ss.length; i++){
            arr[i] = Integer.parseInt(ss[i]);
        }


        for(int i =0; i< arr.length; i++){
            while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                    arr[stack.pop()] = arr[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            arr[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(arr[i]).append(' ');
        }

        System.out.println(sb);

    }
}