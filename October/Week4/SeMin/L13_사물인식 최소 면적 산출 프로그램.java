import java.util.*;
import java.io.*;

public class Main {
    static int res = Integer.MAX_VALUE;
    static int N, K;
    static List<Pair>[] v;
    static class Pair {
        int x, y;

        Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        N = Integer.parseInt(nk[0]);
        K = Integer.parseInt(nk[1]);

        v = new ArrayList[22];
        for (int i = 0; i < 22; i++){
            v[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String[] xyk = br.readLine().split(" ");
            int x = Integer.parseInt(xyk[0]);
            int y = Integer.parseInt(xyk[1]);
            int k = Integer.parseInt(xyk[2]);
            v[k].add(new Pair(x, y));
        }
        func(1, -1001, -1001, 1001, 1001);
        System.out.println(res);

    }

    static void func(int depth, int maxY, int maxX, int minY, int minX){
        if (depth > K) {
            res = Math.min(res, Math.abs(maxX - minX) * Math.abs(maxY - minY));
            return;
        }
        for(Pair p : v[depth]){
            int newMaxX = Math.max(maxX, p.x);
            int newMaxY = Math.max(maxY, p.y);
            int newMinX = Math.min(minX, p.x);
            int newMinY = Math.min(minY, p.y);

            // 새로운 면적을 계산합니다.
            int tmp = Math.abs(newMaxX - newMinX) * Math.abs(newMaxY - newMinY);
            // 면적이 현재 최소값보다 작거나, 첫 번째 좌표를 선택하는 경우
            if (tmp < res || depth == 1) {
                func(depth + 1, newMaxY, newMaxX, newMinY, newMinX); // 다음 좌표 선택
            }
        }


    }
}