import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static char[][] map = new char[5][5];
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int answer;
    static Set<String> seen = new HashSet<>();
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        answer = 0;

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                boolean[][] visited = new boolean[5][5];
                visited[i][j] = true;
                int s = (map[i][j] == 'S') ? 1 : 0;
                int y = (map[i][j] == 'Y') ? 1 : 0;
                dfs(s, y, visited);
            }
        }

        System.out.println(answer);
    }

    static void dfs(int S, int Y, boolean[][] visited) {
        if (Y >= 4) return;
        
        if (S + Y == 7) {
            if (S >= 4) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        sb.append(visited[i][j] ? '1' : '0');
                    }
                }

                if (seen.add(sb.toString())) answer++;
            }
            return;
        }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) continue;

                boolean isNeighbor = false;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) continue;
                    if (visited[nr][nc]) {
                        isNeighbor = true;
                        break;
                    }
                }

                if (!isNeighbor) continue;

                visited[i][j] = true;
                if (map[i][j] == 'S') {
                    dfs(S + 1, Y, visited);
                } else {
                    dfs(S, Y + 1, visited);
                }
                visited[i][j] = false;
            }
        }
    }
}
