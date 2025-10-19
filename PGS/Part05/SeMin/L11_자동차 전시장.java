import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String[] nmk = br.readLine().split(" ");

        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);

        List<Integer>[] edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int x = Integer.parseInt(edge[0]) - 1;
            int y = Integer.parseInt(edge[1]) - 1;
            edges[x].add(y);
        }

        int[] startPoints = new int[k];

        String[] sp = br.readLine().split(" ");
        for (int i = 0; i < k; i++) {
            startPoints[i] = Integer.parseInt(sp[i]) - 1;
        }

        int[][] step = new int[k][n];
        boolean[][] visited = new boolean[k][n];

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < k; i++){
            int sNum = startPoints[i];
            q.offer(sNum);
            visited[i][sNum] = true;

            while(!q.isEmpty()){
                int x = q.poll();
                List<Integer> list = edges[x];
                for(int y :list){
                    if(!visited[i][y]){
                        visited[i][y] = true;
                        step[i][y] = step[i][x] + 1;
                        q.offer(y); 
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            boolean check = false;
            int max = -1;
            for(int j = 0; j < k; j++){
                if(!visited[j][i]){
                    check = true;
                }
                max = Math.max(max, step[j][i]);
            }
            if(!check)
                answer = Math.min(answer, max);
        }

        if(answer == Integer.MAX_VALUE){
            answer = -1;
        }
        
        System.out.println(answer);


    }
}