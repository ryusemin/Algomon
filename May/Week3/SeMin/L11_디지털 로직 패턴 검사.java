import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Long , Integer> map = new HashMap<>();
        long p = 0;
        for(int i = 0; i < K; i++){
            p = p * 2 + (S.charAt(i) - '0');
        }
        map.put(p, 1);
        for (int i = K; i < S.length(); i++) {
            p = p * 2 - (long) (S.charAt(i - K) - '0') * (1L << K) + (S.charAt(i) - '0');
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        boolean flag = false;
        for (int value : map.values()) {
            if (value >= M) {
                flag = true;
                break;
            }
        }

        System.out.println(flag ? 1 : 0);
    }
}
