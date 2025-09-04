class Solution {
    int answer;
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        
        int n = words.length;
        boolean[] visited = new boolean[n];
        dfs(words, visited, begin, target, 0);
        
        return answer;
    }
    
    void dfs(String[] words, boolean[] visited, String now, String target, int depth) {
        if (now.equals(target)) {
            answer = depth;
            return;
        }
        
        for (int i = 0; i < words.length; i++) {
            if (!visited[i]) {
                if (isDiffOne(now, words[i])) {
                    visited[i] = true;
                    dfs(words, visited, words[i], target, depth + 1);
                    visited[i] = false;
                }
            }
        }
    }
    
    
    boolean isDiffOne(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i))
                count++;
        }
        
        return count == 1;
    }
}