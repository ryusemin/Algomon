import java.util.*;
import java.io.*;

public class Main {
    static String[][] classroom;
    static int ans;
    static boolean[] visited;
    static int[] checked = new int[7];
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        classroom = new String[5][5];
        for (int i = 0; i < 5; i++) {
            String[] arr = br.readLine().split("");
            for (int j = 0; j < 5; j++) {
                classroom[i][j] = arr[j];
            }
        }
        comb(0, 0, 0);
        System.out.println(ans);
    }


    static void comb(int cnt, int start, int dasomCnt) {
        if (cnt - dasomCnt > 3) return;

        if (cnt == 7) {
            visited = new boolean[7];
            bfs(checked[0] / 5, checked[0] % 5);
            return;
        }

        for (int i = start; i < 25; i++) {
            int row = i  /5, col = i % 5;
            checked[cnt] = i;
            comb(cnt+1, i+1, (classroom[row][col].equals("S"))? dasomCnt+1: dasomCnt);
        }

    }


    static void bfs(int i, int j) {
        int num = 1;
        visited[0] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {i, j});
        while (!q.isEmpty()) {
            int[] rowCol = q.poll();
            int y = rowCol[0];
            int x = rowCol[1];
            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (ny < 0 || ny >= 5 || nx < 0 || nx >= 5) continue;
                int nxt = 5 * ny + nx;
                for (int k = 0; k < 7; k++) {
                    if (!visited[k] && checked[k] == nxt) {
                        visited[k] = true;
                        num++;
                        q.offer(new int[] {ny, nx});
                    }
                }
            }
        }
        if (num == 7) ans++;
    }
}