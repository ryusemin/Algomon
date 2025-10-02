import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        int H = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);
        int R = Integer.parseInt(inputs[2]);

        int N = (int) Math.pow(2, H+1);
        Queue<Integer>[][] task = new Queue[2][N];
        for(int i = 0 ; i < 2; i++){
            for(int j = 0; j < N; ++j){
                task[i][j] = new ArrayDeque();
            }
        }

        int leaf = (int)Math.pow(2, H);
        for(int i = leaf; i < leaf * 2; i++){
            inputs = br.readLine().split(" ");
            for(int j = 0; j < K; j++)
                task[0][i].add(Integer.parseInt(inputs[j]));
        }

        int result = 0;
        for(int day = 1; day <= R; day++){
            int bit = day % 2;

            // 부서장
            if(!task[bit][1].isEmpty())
                result += task[bit][1].poll();
            
            // 일반 직원
            for(int i = 2; i < leaf; ++i){
                int parent = i / 2;
                if(task[bit][i].isEmpty()) continue;
                task[(i+1) % 2][parent].add(task[bit][i].poll());
            }

            // 말단 직원
            for(int i = leaf; i < leaf * 2; i++){
                int parent = i / 2;
                if(task[0][i].isEmpty()) continue;
                task[(i+1) % 2][parent].add(task[0][i].poll());
            }
        }
        System.out.println(result);
    }
}