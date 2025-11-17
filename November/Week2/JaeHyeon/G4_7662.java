import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    static int t, k;
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (cmd.equals("I")) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (map.isEmpty()) continue;
                    
                    if (num == -1) {
                        int key = map.firstKey();
                        int cnt = map.get(key);
                        if (cnt == 1) {
                            map.remove(key);
                        } else {
                            map.put(key, cnt - 1);
                        }
                    } else {
                        int key = map.lastKey();
                        int cnt = map.get(key);
                        if (cnt == 1) {
                            map.remove(key);
                        } else {
                            map.put(key, cnt - 1);
                        }
                    }
                }
            }

            if (map.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
