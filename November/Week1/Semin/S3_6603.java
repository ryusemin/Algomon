import java.util.*;
import java.io.*;

class Main {
    static int[] arr;
    static int[] list;
    static boolean[] visited;
    static int k;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] numbers = line.split(" ");
            k = Integer.parseInt(numbers[0]);
            arr = new int[k];
            visited = new boolean[k];
            list = new int[6];
    
            for(int i = 1; i <= k; i++){
                arr[i-1] = Integer.parseInt(numbers[i]);
            }

            back(0, 0);

            line = null;
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void back(int start, int depth){
        if(depth == 6){
            for(int val : list){
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i < k; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list[depth] = arr[i];
                back(i, depth + 1);
                visited[i] = false;
            }
        }
    }
}