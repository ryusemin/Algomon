import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int arr[][];
    static int answer = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr= new int[n][n];
        visited = new boolean[n];

        StringTokenizer st;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, -1);
        System.out.println(answer);
    }

    static void dfs(int depth, int now){
        if(depth == n / 2){
            int morning = 0;
            int evening = 0;

            for(int i = 0; i < n; i++){
                if(visited[i]){
                    for(int j = 0; j < n; j++){
                        if(visited[j]) {
                            if(i == j) continue;
                            min += arr[i][j];
                        }
                    }
                }
                else{
                    for(int j = 0; j < n; j++){
                        if(!visited[j]) {
                            if(i == j) continue;
                            m2 += arr[i][j];
                        }
                    }
                }
            }
            int result = Math.abs(morning - evening);

            answer = Math.min(answer, result);
            return;
        }
        for(int i = now + 1; i < n; i++){
            visited[i] = true;
            dfs(depth + 1, i);
            visited[i] = false;
        }
    }
}