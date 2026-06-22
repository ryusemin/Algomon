import java.io.*;
import java.util.*;

public class Main {

    static class Item {
        int val;
        boolean alive;

        Item() {
            this.val = 0;
            this.alive = false;
        }

        Item(int v) {
            this.val = v;
            this.alive = true;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int Q = Integer.parseInt(br.readLine());

        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item());

        StringBuilder sb = new StringBuilder();

        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int op = Integer.parseInt(st.nextToken());

            if (op == 1) {
                int N = Integer.parseInt(st.nextToken());

                for (int i = 0; i < N; i++) {
                    items.add(new Item(Integer.parseInt(st.nextToken())));
                }
            }

            else if (op == 2) {
                int v = Integer.parseInt(st.nextToken());
                items.add(new Item(v));
            }

            else if (op == 3) {
                int idx = Integer.parseInt(st.nextToken());

                if (idx >= 1 && idx < items.size() && items.get(idx).alive) {
                    sb.append(items.get(idx).val).append("\n");
                    items.get(idx).alive = false;
                } else {
                    sb.append(-1).append("\n");
                }
            }

            else if (op == 4) {
                int K = Integer.parseInt(st.nextToken());

                final int INF = (int)1e9;

                int[] dp = new int[K + 1];
                Arrays.fill(dp, INF);
                dp[0] = 0;

                for (int k = 1; k <= K; k++) {
                    for (int i = 1; i < items.size(); i++) {
                        Item item = items.get(i);

                        if (item.alive && item.val <= k && dp[k - item.val] != INF) {
                            dp[k] = Math.min(dp[k], dp[k - item.val] + 1);
                        }
                    }
                }

                sb.append(dp[K] == INF ? -1 : dp[K]).append("\n");
            }

            else {
                int K = Integer.parseInt(st.nextToken());

                ArrayList<Integer> vals = new ArrayList<>();
                int[] freq = new int[3001];

                for (int i = 1; i < items.size(); i++) {
                    Item item = items.get(i);

                    if (item.alive) {
                        vals.add(item.val);
                        freq[item.val]++;
                    }
                }

                int n = vals.size();

                int[] suffix = new int[3002];

                for (int v = 3000; v >= 1; v--) {
                    suffix[v] = suffix[v + 1] + freq[v];
                }

                long ans = 0;

                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        int need = K - vals.get(a) - vals.get(b);

                        if (need <= 0) {
                            ans += n;
                        }
                        else if (need <= 3000) {
                            ans += suffix[need];
                        }
                    }
                }

                sb.append(ans).append("\n");
            }
        }

        System.out.print(sb);
    }
}