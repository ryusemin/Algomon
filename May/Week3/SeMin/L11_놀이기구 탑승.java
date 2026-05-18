import java.util.*;
import java.io.*;

public class Main {
    static int[] dy = {1 , 0, 0, -1};
    static int[] dx = {0, 1, -1, 0};
    static int[] score = {0, 1, 10, 100, 1000};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Set<Integer>[] likes = new HashSet[n * n + 1];
        int[][] rides = new int[n][n];
        int[] friends = new int[n * n + 1];

        for(int i = 1; i <= n * n; i++){
            likes[i] = new HashSet<>();
        }

        StringTokenizer st;
        boolean flag = true;
        for(int i = 0; i < n * n; i++){
            st = new StringTokenizer(br.readLine());

            int now = Integer.parseInt(st.nextToken());
            for(int j = 0; j < 4; j++){
                likes[now].add(Integer.parseInt(st.nextToken()));
            }

            if(flag) {
                rides[1][1] = now;
                flag = false;
                continue;
            }

            int myY = 0;
            int myX = 0;

            int myLike = 0; // 좋아하는 학생 수 최대
            int myEmpty = 0; // 

            for(int y = n - 1; y >= 0; y--){
                for(int x = n - 1; x >= 0; x--){
                    if(rides[y][x] > 0) continue; // 누가 타고있으면 건너뛰기

                    int like = 0; // 좋아하는 학생 수
                    int empty = 0; // 주변 빈공간

                    for(int d = 0; d < 4; d++){
                        int ny = y + dy[d];
                        int nx = x + dx[d];

                        if(ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

                        if(likes[now].contains(rides[ny][nx])) like++;
                        else if(rides[ny][nx] == 0) empty++;
                    }
                    if(like > myLike || (empty >= myEmpty && like == myLike)){
                        myY = y;
                        myX = x;
                        myLike = like;
                        myEmpty = empty;
                    }
                }
            }
            rides[myY][myX] = now;
        }
        int answer = 0;
            for(int y = n - 1; y >= 0; y--){
                for(int x = n - 1; x >= 0; x--){
                    int now = rides[y][x];
                    int like = 0; // 좋아하는 학생 수

                    for(int d = 0; d < 4; d++){
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if(ny < 0 || nx < 0 || ny >= n || nx >= n) continue;

                        if(likes[now].contains(rides[ny][nx])) like++;
                    }
                    friends[now] = like;
                }
            }
            for(int i = 1; i < n *n + 1; i++){
                answer += score[friends[i]];
            }


        System.out.println(answer);
    }
}