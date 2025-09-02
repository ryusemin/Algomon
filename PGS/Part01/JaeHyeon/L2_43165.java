class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        dfs(numbers, target, 0, 0);
        
        return answer;
    }
    
    public void dfs(int[] numbers, int target, int depth, int now) {
        if (depth == numbers.length - 1) {
            if (now + numbers[numbers.length - 1] == target) answer++;
            if (now - numbers[numbers.length - 1] == target) answer++;
            return;
        }
        
        dfs(numbers, target, depth + 1, now + numbers[depth]);
        dfs(numbers, target, depth + 1, now - numbers[depth]);
    }
}