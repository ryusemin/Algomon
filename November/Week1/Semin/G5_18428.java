import java.util.*;
import java.io.*;

class Main {
    static class Node{
        int x, y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static String[][] map;
    static String[][] copy;

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static String result = "NO";
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new String[N + 1][N + 1];

        for (int i = 1; i <= N ; i++ ) {
            String[] s = br.readLine().split(" ");
            for (int j = 1; j <= N ; j++ ) {
                map[i][j] = s[j - 1];
            }   
        }

        dfs(0);
        System.out.println(result);
        
    }

    static void dfs(int obj){
        if(obj == 3){
            if(bfs()){
                result = "YES";
            }
            return;
        }
        for (int i = 1; i <= N ; i++ ) {
            for (int j = 1; j <= N ; j++ ) {
                if(map[i][j].equals("X")){
                    map[i][j] = "O";
                    dfs(obj + 1);
                    map[i][j] = "X";
                }
            }   
        }
    }
    static boolean bfs(){
        Queue<Node> q = new LinkedList<>();
        copy = new String[N+1][N+1];
        for (int i = 1; i <= N ; i++ ) {
            for (int j = 1; j <= N ; j++ ) {
                copy[i][j] = map[i][j];
                if(copy[i][j].equals("T")){
                    q.offer(new Node(i, j));
                }
            }   
        }
        while (!q.isEmpty()) {
            Node cv = q.poll();
            for (int i = 0; i < 4 ; i++ ) {
                int nx = cv.x;
                int ny = cv.y;
                while (true) {
                    nx += dx[i];
                    ny += dy[i];
                    if(nx < 1 || ny < 1 || nx > N || ny > N) break;
                    if(copy[nx][ny].equals("O")) break;
                    if(copy[nx][ny].equals("S")) return false;
                }
            }
        }
        return true;
    }
}