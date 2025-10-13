import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int[][] map = new int[101][101];
        int[][] visited = new int[101][101];
        
        for(int[] r : rectangle){
            int lx = r[0] * 2, ly = r[1] * 2, rx = r[2] * 2, ry = r[3] * 2;
            
            for (int x = lx; x <= rx; x++) {
                for (int y = ly; y <= ry; y++) {
                    map[x][y] = 1;
                }
            }
        }
        
        for(int[] r : rectangle){
            int lx = r[0] * 2, ly = r[1] * 2, rx = r[2] * 2, ry = r[3] * 2;
                    
            for (int x = lx + 1; x <= rx - 1; x++) {
                for (int y = ly + 1; y <= ry - 1; y++) {
                    map[x][y] = 0;
                }
            }
        }
        
        Point start = new Point(characterX * 2, characterY * 2);
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.x][start.y] = 1;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            if (now.x == (itemX * 2) && now.y == (itemY * 2)) {
                return (visited[itemX * 2][itemY * 2] - 1) / 2;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                if (nx < 0 || nx >= 101 || ny < 0 || ny >= 101) continue;
                if (visited[nx][ny] != 0) continue;
                if (map[nx][ny] == 0) continue;

                visited[nx][ny] = visited[now.x][now.y] + 1;
                queue.add(new Point(nx, ny));
            }
        }
        
        return -1;
    }
}

class Point {
    int x, y;
    Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
}