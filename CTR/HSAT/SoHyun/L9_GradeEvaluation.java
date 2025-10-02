import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] scores = new int[3][n];
        int[] totalScores = new int[n];

        int[][] finalRanks = new int[4][n];

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int score = Integer.parseInt(st.nextToken());
                scores[i][j] = score;
                totalScores[j] += score;
            }
        }


        for (int i = 0; i < 4; i++) {
            int[] currentScores;
            if (i < 3) {
                currentScores = scores[i];
            } else {
                currentScores = totalScores;
            }

            int[][] rankData = new int[n][2];
            for (int j = 0; j < n; j++) {
                rankData[j][0] = currentScores[j];
                rankData[j][1] = j;
            }

            Arrays.sort(rankData, (a, b) -> b[0] - a[0]);

            int rank = 1;

            finalRanks[i][rankData[0][1]] = rank;

            for (int j = 1; j < n; j++) {
                if (rankData[j][0] != rankData[j-1][0]) {
                    rank = j + 1;
                }

                finalRanks[i][rankData[j][1]] = rank;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(finalRanks[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb.toString());
    }
}