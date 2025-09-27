import java.util.*;

class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public int solution(int n, int[] weak, int[] dist) {
        
        dfs(dist, new boolean[dist.length], 0, new ArrayList<>());
        
        int[] newWeak = new int[weak.length * 2];
        for (int i = 0; i < weak.length; i++) {
            newWeak[i] = weak[i];
            newWeak[i + weak.length] = weak[i] + n;
        }
        
        int answer = dist.length + 1;
        for (List<Integer> list : result) {
            int len = weak.length;

            for (int start = 0; start < len; start++) {
                int count = 1;
                int position = newWeak[start] + list.get(count - 1);

                for (int idx = start; idx < start + len; idx++) {
                    if (newWeak[idx] > position) {
                        count++;
                        if (count > list.size()) break;
                        position = newWeak[idx] + list.get(count - 1);
                    }
                }
                answer = Math.min(answer, count);
            }
        }     
        
        return answer == dist.length + 1 ? -1 : answer;
    }
    
    void dfs(int[] dist, boolean[] visited, int depth, List<Integer> list) {
        
        if (depth == dist.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < dist.length; i++) {
            if (!visited[i]) {
                list.add(dist[i]);
                visited[i] = true;
                dfs(dist, visited, depth + 1, list);
                list.remove(list.size() - 1);
                visited[i] = false;
                
            }
        }
    }
}