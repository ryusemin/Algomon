import java.util.*;

public class Solution {
    int answer;
    int N;
    int[] weak;
    int[] dist;
    int[] newWeak;

    public int solution(int n, int[] weak, int[] dist) {
        this.N = n;
        this.weak = weak;
        this.dist = dist;
        this.answer = dist.length + 1;

        int len = weak.length;
        newWeak = new int[len * 2];

        for (int i = 0; i < len; i++) {
            newWeak[i] = weak[i];
            newWeak[i + len] = weak[i] + n;
        }

        permutation(0, new boolean[dist.length], new int[dist.length]);

        return (answer == dist.length + 1) ? -1 : answer;
    }

    private void permutation(int cnt, boolean[] isSelected, int[] distCase) {
        if (cnt == dist.length) {
            check(distCase);
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (isSelected[i]) continue;
            distCase[cnt] = dist[i];
            isSelected[i] = true;
            permutation(cnt + 1, isSelected, distCase);
            isSelected[i] = false;
        }
    }

    private void check(int[] distCase) {
        int len = weak.length;

        for (int start = 0; start < len; start++) {
            int cnt = 1;
            int position = newWeak[start] + distCase[cnt - 1];

            for (int idx = start; idx < start + len; idx++) {
                if (newWeak[idx] > position) {
                    cnt++;
                    if (cnt > distCase.length) break;
                    position = newWeak[idx] + distCase[cnt - 1];
                }
            }
            answer = Math.min(answer, cnt);
        }
    }
}