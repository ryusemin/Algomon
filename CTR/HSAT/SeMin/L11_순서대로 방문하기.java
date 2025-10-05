import java.util.*;
import java.io.*;

public class Main {
    static class Car{
        int x, y;
        int check;
        boolean[][] visited;

        public Car(int x, int y, int check, boolean[][] visited){
            this.x = x;
            this.y = y;
            this.check = check;
            this.visited = visited;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] dx = {1 , 0, - 1, 0 };
        int[] dy = {0,  1, 0 , -1 };  

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] aaa = br.readLine().split(" ");
        int n = Integer.parseInt(aaa[0]);
        int m = Integer.parseInt(aaa[1]); // 지점 개수
        int[][] grid = new int[n + 1][n + 1];

        for(int i = 1; i <= n; i++){
            String[] arr = br.readLine().split(" ");
            for(int j = 1; j <= n; j++){
                grid[i][j] = Integer.parseInt(arr[j-1]);
            }
        }
        
        int[][] targets = new int[m][2];

        for (int i = 0; i < m; i++) {
            String[] a = br.readLine().split(" ");
            targets[i][0] = Integer.parseInt(a[0]);
            targets[i][1] = Integer.parseInt(a[1]);
        }

        Queue<Car> q = new LinkedList<>();
        boolean[][] visite = new boolean[n+1][n+1];
        visite[targets[0][0]][targets[0][1]] = true;

        if(grid[targets[0][0]][targets[0][1]] == 0 ){
            q.offer(new Car(targets[0][0], targets[0][1], 1, visite));
        }   

        int count = 0;

        while(!q.isEmpty()){
            Car car = q.poll();
            int x = car.x;
            int y = car.y;
            int check = car.check;
            boolean[][] visited = car.visited;
            if(check == m) {
                count++;
                continue;
            }

            for(int i =0; i< 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx > 0 && ny > 0 && nx <= n && ny <= n){
                    if (!visited[nx][ny] && grid[nx][ny] == 0) {
                        boolean[][] newVisited = new boolean[n+1][n+1];
                        for (int a = 1; a <= n; a++) {
                            newVisited[a] = Arrays.copyOf(visited[a], n+1);
                        }
                        newVisited[nx][ny] = true;

                        int nextCheck = check;
                        if (nx == targets[check][0] && ny == targets[check][1]) nextCheck++;

                        q.offer(new Car(nx, ny, nextCheck, newVisited));
                    }
                }
            }
        }
        System.out.println(count);

    }
}