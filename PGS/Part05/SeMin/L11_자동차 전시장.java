import java.util.*;
import java.io.*;

public class Main {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final int MAX_K = 10;
    public static final int MAX_N = 100000;
    
    public static int n, m, k;
    
    public static ArrayList<Integer>[] edges = new ArrayList[MAX_N];
    public static int[] startPoints = new int[MAX_K];
    
    public static boolean[][] visited = new boolean[MAX_K][MAX_N];
    public static int[][] step = new int[MAX_K][MAX_N];
    
    public static int ans = INT_MAX;
    

    public static void bfs(int i) {
        int sNum = startPoints[i];
    
        for(int j = 0; j < n; j++)
            step[i][j] = -1;
    
        Queue<Integer> q = new LinkedList<>();
        visited[i][sNum] = true;
        step[i][sNum] = 0;
        q.add(sNum);
    
        while(!q.isEmpty()) {
            int x = q.poll();
            for(int j = 0; j < edges[x].size(); j++) {
                int y = edges[x].get(j);
    
                if(!visited[i][y]) {
                    visited[i][y] = true;
                    step[i][y] = step[i][x] + 1;
                    q.add(y);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        
        String[] nmk = br.readLine().split(" ");
        n = Integer.parseInt(nmk[0]);
        m = Integer.parseInt(nmk[1]);
        k = Integer.parseInt(nmk[2]);
        
        for(int i = 0; i < n; i++)
            edges[i] = new ArrayList<>();
        
        for(int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int x = Integer.parseInt(edge[0]);
            int y = Integer.parseInt(edge[1]);
            x--; y--;
            edges[x].add(y);
        }
        String[] sp = br.readLine().split(" ");
        for(int i = 0; i < k; i++) {
            startPoints[i] = Integer.parseInt(sp[i]) - 1;
        }
        
        for(int i = 0; i < k; i++)
            bfs(i);
        
        for(int i = 0; i < n; i++) {
            boolean impossible = false;
            int maxT = -1;
            for(int j = 0; j < k; j++) {
                if(!visited[j][i])  
                    impossible = true;
                
                maxT = Math.max(maxT, step[j][i]);
            }
            if(!impossible)
                ans = Math.min(ans, maxT);
        }

        if(ans == INT_MAX)
            ans = -1;
        
        System.out.print(ans);
    }
}
