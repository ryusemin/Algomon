import java.util.*;
import java.io.*;

class Cctv {
    int x, y, num;
    List<Integer> dirs = new ArrayList<>();

    Cctv(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }

    public void addDir(int dir){
        dirs.add(dir);
    }
    
}

class Main {
	public static int N, M;
	public static int[][] map;
	public static List<Cctv> list;
	public static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 시계방향 순서 
	public static int[] dy = {0, 1, 0, -1};
	public static int min = Integer.MAX_VALUE;
    public static int area;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        
        String[] NM = br.readLine().split(" ");
        N = Integer.parseInt(NM[0]);
        M = Integer.parseInt(NM[1]);
        map = new int[N][M];
        list = new ArrayList<>();

        for (int i = 0; i < N ; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M ; j++) {
                int now = Integer.parseInt(line[j]);
                map[i][j] = now;
                if (now == 0) area++;
                if (now != 0 && now != 6){
                    list.add(new Cctv(i, j, now));
                }
                
            }   
        }
        dfs(0, new Cctv[list.size()]);
        System.out.println(min);
    }

    static void dfs(int depth, Cctv[] cctvs){
        if(depth == list.size()){
            countArea(cctvs, new boolean[N][M]);
            return;
        }
        Cctv selected = list.get(depth);
        for (int i = 0; i < 4; i++ ) { 
            Cctv c = new Cctv(selected.x, selected.y, selected.num);
            switch (c.num) {
                case 1:
                    c.addDir(i);
                    cctvs[depth] = c;
                    dfs(depth + 1, cctvs);
                    break;
                case 2:
                    if(i >= 2) return;
                    c.addDir(i);
                    c.addDir(i+2);
                    cctvs[depth] = c;
                    dfs(depth + 1, cctvs);
                    break;
                case 3:
                    c.addDir(i);
                    c.addDir((i+1) % 4);
                    cctvs[depth] = c;
                    dfs(depth + 1, cctvs);
                    break;
                case 4:
                    c.addDir(i);
                    c.addDir((i + 1) % 4);
                    c.addDir((i + 2) % 4);
                    cctvs[depth] = c;
                    dfs(depth + 1, cctvs);
                    break;
                case 5:
                    c.addDir(i);
                    c.addDir((i + 1) % 4);
                    c.addDir((i + 2) % 4);
                    c.addDir((i + 3) % 4);
                    cctvs[depth] = c;
                    dfs(depth + 1, cctvs);
            }
        }
    }

    static void countArea(Cctv[] cctvs, boolean[][] visited){
    int cnt = 0;
    for (int i = 0; i < cctvs.length; i++ ) {
        Cctv c = cctvs[i];
        for (int j = 0; j < c.dirs.size(); j++ ) {
            int dir = c.dirs.get(j);
            int nx = c.x + dx[dir];
            int ny = c.y + dy[dir];
            while (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if(map[nx][ny] == 0){
                    if(!visited[nx][ny]){
                        cnt++;
                        visited[nx][ny] = true;
                    }
                }
                else if(map[nx][ny] == 6) break;
                nx += dx[dir];
                ny += dy[dir];
            }
            
        }
    }
    min = Math.min(min, area - cnt);
}
}

