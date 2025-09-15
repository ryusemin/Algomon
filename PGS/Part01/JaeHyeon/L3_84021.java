import java.util.*;

class Solution {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    public int solution(int[][] game_board, int[][] table) {
        
        List<List<Point>> holes = extract(game_board, 0);
        List<List<Point>> pieces = extract(table, 1);
        
        for (int i = 0; i < holes.size(); i++) {
            holes.set(i, normalize(holes.get(i)));
        }
        
        boolean[] used = new boolean[pieces.size()];
        int answer = 0;

        for (List<Point> hole : holes) {
            int holeSize = hole.size();

            for (int i = 0; i < pieces.size(); i++) {
                if (used[i]) continue;
                if (pieces.get(i).size() != holeSize) continue;

                boolean matched = false;
                for (List<Point> rot : rotations(pieces.get(i))) {
                    if (equalsPiece(hole, rot)) {
                        used[i] = true;
                        answer += holeSize;
                        matched = true;
                        break;
                    }
                }
                
                if (matched) break;
            }
        }
        return answer;
    }
    
    // ====== 회전 관련 ======
   // 90도 회전: (r, c) -> (c, -r)
    List<Point> rotate90(List<Point> piece) {
        List<Point> rot = new ArrayList<>(piece.size());
        for (Point p : piece) {
            rot.add(new Point(p.c, -p.r));
        }
        return normalize(rot);
    }

    // 180도 회전: (r, c) -> (-r, -c)
    List<Point> rotate180(List<Point> piece) {
        List<Point> rot = new ArrayList<>(piece.size());
        for (Point p : piece) {
            rot.add(new Point(-p.r, -p.c));
        }
        return normalize(rot);
    }

    // 270도 회전: (r, c) -> (-c, r)
    List<Point> rotate270(List<Point> piece) {
        List<Point> rot = new ArrayList<>(piece.size());
        for (Point p : piece) {
            rot.add(new Point(-p.c, p.r));
        }
        return normalize(rot);
    }

    // 전체 회전 결과 반환
    List<List<Point>> rotations(List<Point> piece) {
        List<List<Point>> res = new ArrayList<>(4);
        List<Point> p0 = normalize(piece);
        res.add(p0);
        res.add(rotate90(p0));
        res.add(rotate180(p0));
        res.add(rotate270(p0));
        return res;
    }

    boolean equalsPiece(List<Point> a, List<Point> b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).r != b.get(i).r || a.get(i).c != b.get(i).c) return false;
        }
        return true;
    }
    
    List<Point> normalize(List<Point> piece) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        
        for (Point p : piece) {
            if (p.r < minR) minR = p.r;
            if (p.c < minC) minC = p.c;
        }
        
        List<Point> norm = new ArrayList<>(piece.size());
        for (Point p : piece) {
            norm.add(new Point(p.r - minR, p.c - minC));
        }
        
        norm.sort((a, b) -> {
            if (a.r == b.r) return a.c - b.c;
            return a.r - b.r;
        });
        return norm;
    }
    
    List<List<Point>> extract(int[][] table, int target) {
        int n = table.length;
        boolean[][] visited = new boolean[n][n];
        List<List<Point>> pieces = new ArrayList<>();
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (table[r][c] == target && !visited[r][c]) {
                    List<Point> piece = new ArrayList<>();
                    Queue<Point> q = new ArrayDeque<>();
                    
                    q.add(new Point(r, c));
                    visited[r][c] = true;
                    
                    while(!q.isEmpty()) {
                        Point now = q.poll();
                        piece.add(now);
                        
                        for (int d = 0; d < 4; d++) {
                            int nr = now.r + dr[d];
                            int nc = now.c + dc[d];
                            if (nr >= 0 && nr < n && nc >= 0 && nc < n 
                                && table[nr][nc] == target && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                q.add(new Point(nr, nc));
                            }
                        }
                    }
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }
}

class Point {
    int r, c;
    Point (int r, int c) {
        this.r = r;
        this.c = c;
    }
}