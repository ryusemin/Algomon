import java.util.*;
import java.io.*;

class Solution {
    int[] di = {-1, 0, 1, 0};
    int[] dj = {0, 1, 0, -1};

    Set<String>[] move;
    int n;
    StringBuilder sb = new StringBuilder();
    int answer = 0;
    int blockSize = 1;
    boolean[] isUsed;

    public int solution(int[][] game_board, int[][] table) {
        n = table.length;
        addLabel(table);
        move = new Set[blockSize + 1];
        for (int i = 0; i <= blockSize; i++) {
            move[i] = new HashSet<>();
        }
        isUsed = new boolean[blockSize + 1];

        down(table, 0);
        right(table, 1);
        up(table, 2);
        left(table, 3);

        gameBoard(game_board);

        return answer/2;
    }

    private void addLabel(int[][] table) {
        boolean[][] v = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] == 1 && !v[i][j]) {
                    v[i][j] = true;
                    table[i][j] = blockSize;
                    labelDfs(i, j, 0, v, table);
                    blockSize++;
                }
            }
        }
    }

    private void labelDfs(int x, int y, int wall, boolean[][] v, int[][] table) {
        for (int i = 0; i < 4; i++) {
            int dx = x + di[i];
            int dy = y + dj[i];
            if (dx < 0 || dx >= n || dy < 0 || dy >= n) {
                continue;
            }

            if (v[dx][dy]) {
                continue;
            }

            if (table[dx][dy] == wall) {
                continue;
            }

            v[dx][dy] = true;
            table[dx][dy] = blockSize;
            labelDfs(dx, dy, wall, v, table);
        }
    }

    private void gameBoard(int[][] game_board) {
        boolean[][] v = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (game_board[i][j] == 0 && !v[i][j]) {
                    v[i][j] = true;
                    dfs(i, j, 1, v, game_board, 0);

                    String result = sb.toString();
                    sb.setLength(0);

                    for (int k = 1; k <= blockSize; k++) {
                        if (!isUsed[k] && move[k].contains(result)) {
                            isUsed[k] = true;
                            answer += result.length() + 2;
                            break;
                        }
                    }
                }
            }
        }
    }

    private void down(int[][] table, int turn) {
        boolean[][] v = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (table[i][j] != 0 && !v[i][j]) {
                    int label = table[i][j];
                    v[i][j] = true;
                    dfs(i, j, 0, v, table, turn);
                    move[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }

    private void right(int[][] table, int turn) {
        boolean[][] v = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j >=0; j--) {
                if (table[j][i] != 0 && !v[j][i]) {
                    int label = table[j][i];
                    v[j][i] = true;
                    dfs(j, i, 0, v, table, turn);
                    move[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }

    private void up(int[][] table, int turn) {
        boolean[][] v = new boolean[n][n];
        for (int i = n-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (table[i][j] != 0 && !v[i][j]) {
                    int label = table[i][j];
                    v[i][j] = true;
                    dfs(i, j, 0, v, table, turn);
                    move[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }

    private void left(int[][] table, int turn) {
        boolean[][] v = new boolean[n][n];
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (table[j][i] != 0 && !v[j][i]) {
                    int label = table[j][i];
                    v[j][i] = true;
                    dfs(j, i, 0, v, table, turn);
                    move[label].add(sb.toString());
                    sb.setLength(0);
                }
            }
        }
    }

    private void dfs(int x, int y, int wall, boolean[][] v, int[][]table, int turn) {
        for (int i = 0; i < 4; i++) {
            int dx = x + di[(i-turn+4) % 4];
            int dy = y + dj[(i-turn+4) % 4];

            if (dx < 0 || dx >= n || dy < 0 || dy >= n) {
                continue;
            }

            if (v[dx][dy]) {
                continue;
            }

            if (table[dx][dy] == wall) {
                continue;
            }

            v[dx][dy] = true;
            sb.append(i);
            dfs(dx, dy, wall, v, table, turn);
            sb.append(".");
        }
    }

}