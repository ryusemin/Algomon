import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] list = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < N; i++) {
            int sum = list[0].get(i) + list[1].get(i) + list[2].get(i);
            list[3].add(sum);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            List<Integer> sorted = new ArrayList<>(list[i]);
            Collections.sort(sorted, (a, b) -> b - a);
            
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < N; j++) {
                int v = sorted.get(j);
                map.putIfAbsent(v, j + 1);
            }

            for (int j = 0; j < N; j++) {
                int v = list[i].get(j);
                sb.append(map.get(v)).append(' ');
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}