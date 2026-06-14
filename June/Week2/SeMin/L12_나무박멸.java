import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, C;
    static int[][] A;
    static int[][] can_grow;

    static int[][] dxy = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int[][] dxy_diagonal = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        can_grow = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long total_killed = 0;

        for (int year = 1; year <= M; year++) {
            step1_tree_growth();
            step2_tree_propagation(year);

            Pos pos = step3_pick_position();

            if (pos.x != -1) {
                total_killed += step4_spray(year, pos.x, pos.y);
            }
        }

        System.out.println(total_killed);
    }

    static boolean in_range(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static void step1_tree_growth() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] <= 0) continue;

                int cnt = 0;

                for (int[] dir : dxy) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];

                    if (in_range(nx, ny) && A[nx][ny] > 0) {
                        cnt++;
                    }
                }

                A[i][j] += cnt;
            }
        }
    }

    static void step2_tree_propagation(int year) {
        int[][] added_tree = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] <= 0) continue;

                List<Pos> blank = new ArrayList<>();

                for (int[] dir : dxy) {
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if (in_range(x, y) && A[x][y] == 0 && can_grow[x][y] <= year) {
                        blank.add(new Pos(x, y));
                    }
                }

                if (!blank.isEmpty()) {
                    int num_to_add = A[i][j] / blank.size();

                    for (Pos pos : blank) {
                        added_tree[pos.x][pos.y] += num_to_add;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] += added_tree[i][j];
            }
        }
    }

    static int get_score(int x, int y) {
        int score = A[x][y];

        for (int[] dir : dxy_diagonal) {
            for (int k = 1; k <= K; k++) {
                int nx = x + dir[0] * k;
                int ny = y + dir[1] * k;

                if (!in_range(nx, ny) || A[nx][ny] <= 0) {
                    break;
                }

                score += A[nx][ny];
            }
        }

        return score;
    }

    static Pos step3_pick_position() {
        int max_score = -1;
        Pos best_pos = new Pos(-1, -1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] > 0) {
                    int score = get_score(i, j);

                    if (score > max_score) {
                        max_score = score;
                        best_pos = new Pos(i, j);
                    }
                }
            }
        }

        return best_pos;
    }

    static int step4_spray(int year, int x, int y) {
        int total_killed = 0;

        total_killed += A[x][y];
        A[x][y] = 0;

        can_grow[x][y] = year + C + 1;

        for (int[] dir : dxy_diagonal) {
            for (int k = 1; k <= K; k++) {
                int nx = x + dir[0] * k;
                int ny = y + dir[1] * k;

                if (!in_range(nx, ny) || A[nx][ny] < 0) {
                    break;
                }

                can_grow[nx][ny] = year + C + 1;

                total_killed += A[nx][ny];

                if (A[nx][ny] == 0) {
                    break;
                }

                A[nx][ny] = 0;
            }
        }

        return total_killed;
    }
}