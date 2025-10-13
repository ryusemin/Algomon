import java.util.*;
import java.io.*;

public class Main
{
    public static class Contest{
        int id;
        int score;

        Contest(int id, int score){
            this.id = id;
            this.score = score;
        }
    }

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        Contest[][] c = new Contest[4][n]; 
        for(int i = 0; i < 3; i++){
            String[] st = br.readLine().split(" ");
            for(int j = 0; j < n; j++){
                c[i][j] = new Contest(j, Integer.parseInt(st[j]));
            }
        }

        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = 0; j < 3; j++){
                sum += c[j][i].score;
            }
            c[3][i] = new Contest(i, sum);
        }

        for(Contest[] con : c){
            Arrays.sort(con, (Contest o1, Contest o2) -> o2.score - o1.score);
        }

        int[][] r = new int[4][n];
        
        for(int i = 0; i < 4; i++){
            int rank = 1;
            int cnt = 1;
            r[i][c[i][0].id] = rank;
            for(int j=1; j<n; j++){
                if(c[i][j-1].score == c[i][j].score){
                    r[i][c[i][j].id] = rank;
                    cnt++;
                }
                else {
                    rank += cnt;
                    r[i][c[i][j].id] = rank;
                    cnt = 1;
                }
            }
        }

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < n; j++){
                System.out.print(r[i][j] + " ");
            }
            System.out.println();
        }
    }
}