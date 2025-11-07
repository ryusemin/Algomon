import java.util.*;
import java.io.*;

class Main {
    static int N, L, R, X;
    static int[] problem;
    static boolean[] visited;
    static int result = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nlrx = br.readLine().split(" ");
        N = Integer.parseInt(nlrx[0]);
        L = Integer.parseInt(nlrx[1]);
        R = Integer.parseInt(nlrx[2]);
        X = Integer.parseInt(nlrx[3]);
        
        visited = new boolean[N];
        problem = new int[N];
        
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            problem[i] = Integer.parseInt(s[i]);
        }  

        back(0, 0, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        System.out.println(result);
    }
    static void back(int start, int count, int level, int easy, int hard){
       if(count >= 2){
            if(L <= level && level <= R && hard - easy >= X) {
                result++;
            }
        }
        for(int i = start; i < N; i++){
            if(!visited[i]){
                int e = Math.min(easy, problem[i]);
                int h = Math.max(hard, problem[i]);
                visited[i] = true;
                back(i+1, count + 1, level + problem[i], e, h);
                visited[i] = false;
            }
        }
    }
    
}