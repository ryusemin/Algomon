import java.util.*;
import java.io.*;

class S2_17103{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        boolean[] num = new boolean[1000001];
        num[0] = true;
        num[1] = true;


        for (int i = 2; i * i <= 1000000; i++) {
            if (!num[i]) {
                for (int j = i + i; j <= 1000000; j += i) {
                    num[j] = true;
                }
            }
        }

        for(int tc = 0; tc < T; tc++) {
            int temp = Integer.parseInt(br.readLine());
            int answer = 0;
            for (int j = 2; j <= temp / 2; j++) {
                if (!num[j] && !num[temp - j]) answer++;
            }
            System.out.println(answer);
        }
    }
}


