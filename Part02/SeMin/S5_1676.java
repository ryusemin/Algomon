import java.io.*;
import java.util.*;

class S5_1676 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for(int i = 2; i <=N; i++){
            int r = i;
            while(r % 5 == 0){
                answer++;
                r /= 5;
            }
        }
        System.out.println(answer);
    }

}