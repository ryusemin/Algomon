import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 128;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken()) - 1;
        int M = Integer.parseInt(st.nextToken());

        int[][] cnt = new int[MAX][MAX];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length() - 1; j++) {
                char c = s.charAt(j);
                char next = s.charAt(j + 1);
                cnt[c][next]++;
            }
        }

        int[] next = new int[MAX];
        Arrays.fill(next, -1);

        for (int c = 0; c < MAX; c++) {
            int best = -1;
            int maxCnt = -1;

            for (int d = 0; d < MAX; d++) {
                if (cnt[c][d] > 0) {
                    if (cnt[c][d] > maxCnt || (cnt[c][d] == maxCnt && d < best)) {
                        maxCnt = cnt[c][d];
                        best = d;
                    }
                }
            }
            next[c] = best;
        }

        List<Character> seq = new ArrayList<>();
        Map<Integer, Integer> visited = new HashMap<>();

        int cur = '[';

        while (true) {
            if (visited.containsKey(cur)) {
                break;
            }
            visited.put(cur, seq.size());
            seq.add((char) cur);

            if (cur == ']') break;

            cur = next[cur];
        }

        int cycleStart = visited.get(cur);
        int prefixLen = cycleStart;
        int cycleLen = seq.size() - cycleStart;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            long idx = K + i;

            if (idx < seq.size()) {
                sb.append(seq.get((int) idx));
            } else {
                if (seq.get(seq.size() - 1) == ']') {
                    sb.append('.');
                } else {
                    if (idx < prefixLen) {
                        sb.append(seq.get((int) idx));
                    } else {
                        long pos = (idx - prefixLen) % cycleLen;
                        sb.append(seq.get((int) (prefixLen + pos)));
                    }
                }
            }
        }

        System.out.println(sb);
    }
}