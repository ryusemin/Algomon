import java.util.*;

class Solution {
    int[] dy = {0, 0, -1, 1};
    int[] dx = {-1, 1, 0, 0};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        
        int[][] map = new int[101][101];
        int[][] visited = new int[101][101];
        
        // 사각형 영역 채우기
        for(int[] r : rectangle){
            int lx = r[0] * 2, ly = r[1] * 2, rx = r[2] * 2, ry = r[3] * 2;
            
            for (int y = ly; y <= ry; y++) {
                for (int x = lx; x <= rx; x++) {
                    map[y][x] = 1;
                }
            }
        }
        
        // 사각형 내부 비우기
        for(int[] r : rectangle){
            int lx = r[0] * 2, ly = r[1] * 2, rx = r[2] * 2, ry = r[3] * 2;
                    
            for (int y = ly + 1; y <= ry - 1; y++) {
                for (int x = lx + 1; x <= rx - 1; x++) {
                    map[y][x] = 0;
                }
            }
        }
        
        // BFS
        Point start = new Point(characterY * 2, characterX * 2);
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start.y][start.x] = 1;
        
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            
            if (now.x == (itemX * 2) && now.y == (itemY * 2)) {
                return (visited[itemY * 2][itemX * 2] - 1) / 2;
            }
            
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                
                if (nx < 0 || nx >= 101 || ny < 0 || ny >= 101) continue;
                if (visited[ny][nx] != 0) continue;
                if (map[ny][nx] == 0) continue;

                visited[ny][nx] = visited[now.y][now.x] + 1;
                queue.add(new Point(ny, nx));
            }
        }
        
        return -1;
    }
}

class Point {
    int x, y;
    Point (int y, int x) {
        this.y = y;
        this.x = x;
    }
}