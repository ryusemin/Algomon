import java.util.*;
import java.io.*;

class G3_17299 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] ss = br.readLine().split(" ");
        int[] arr = new int[ss.length];

        Deque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i =0; i< ss.length; i++){
            int num = Integer.parseInt(ss[i]);
            arr[i] = num;
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }


        for(int i =0; i< arr.length; i++){
            while(!stack.isEmpty() && map.get(arr[stack.peek()]) < map.get(arr[i])) {
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