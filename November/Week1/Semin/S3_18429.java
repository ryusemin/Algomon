import java.util.*;
import java.io.*;

class Main {
    static int N;
    static int K;
    static int[] kit;
    static boolean[] visited;
    static int result = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NK = br.readLine().split(" ");
        N = Integer.parseInt(NK[0]);
        K = Integer.parseInt(NK[1]);
        visited = new boolean[N];
        kit = new int[N];
        
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            kit[i] = Integer.parseInt(s[i]);
        }  

        back(0, 500);
        System.out.println(result);
    }
    static void back(int depth, int weight){
        if(depth == N){
            if(weight >= 500){
                result++;
                return;
            }
        }
        for(int i = 0; i < N; i++){
            if(!visited[i] && weight + kit[i] - K >= 500){
                visited[i] = true;
                back(depth + 1, weight + kit[i] - K);
                visited[i] = false;
            }
        }
    }
    
}