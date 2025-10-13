class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, target, 0, -1);
        return answer;
    }

    static public int dfs(int[] numbers, int target, int sum, int n){
        if(n == numbers.length - 1){
            return (sum == target)? 1 : 0;
        }

        return dfs(numbers, target, sum + numbers[n+1], n + 1) + dfs(numbers, target, sum - numbers[n+1], n + 1);
    }
}