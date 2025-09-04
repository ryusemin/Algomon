import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] v = new boolean[words.length];
        Queue<String> q = new LinkedList<>();

        q.add(begin);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String c = q.poll();

                if (c.equals(target))
                    return answer;

                for (int j = 0; j < words.length; j++) {
                    if (!v[j] && diff(c, words[j])) {
                        v[j] = true;
                        q.add(words[j]);
                    }
                }
            }
            answer++;
        }
        return 0;
    }

    public boolean diff(String a, String b) {
        int cnt = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                cnt++;
        }

        return cnt == 1;
    }
}