import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int n, k;
    static List<List<Point>> list = new ArrayList<>();
    static long best = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(--c).add(new Point(x, y));
        }

        list.sort((l1, l2) -> l1.size() - l2.size());

        dfs(0, 0, 0, 0, 0);

        System.out.println(best);
    }

    static void dfs(int idx, int minX, int maxX, int minY, int maxY) {
        if (idx == k) {
            long area = (long)(maxX - minX) * (long)(maxY - minY);
            best = Math.min(best, area);
            return;
        }

        if (idx > 0) {
            long now = (long)(maxX - minX) * (long)(maxY - minY);
            if (now >= best) return;
            if (best == 0) return;
        }

        for (Point p : list.get(idx)) {
            if (idx == 0) {
                dfs(idx + 1, p.x, p.x, p.y, p.y);
            } else {
                int nminX = Math.min(minX, p.x);
                int nmaxX = Math.max(maxX, p.x);
                int nminY = Math.min(minY, p.y);
                int nmaxY = Math.max(maxY, p.y);
                dfs(idx + 1, nminX, nmaxX, nminY, nmaxY);
            }
        }
    }
}

class Point {
    int x, y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}