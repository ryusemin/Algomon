class Solution {
	public int solution(int[] stones, int k) {
		int answer = 0;

		int low = 1;
		int high = 200000000;

		while(low <= high) {
			int mid = (low + high) / 2;

			if(!check(stones, k, mid)) {
				high = mid - 1;
			} else {
				low = mid + 1;
				answer = Math.max(answer, mid);
			}
		}

		return answer;
	}

	boolean check(int[] stones, int k, int count) {
		int jump = 0;
		for(int s : stones) {
			if(s < count) {
				jump++;
			} else {
				jump = 0;
			}

			if(jump == k) {
				return false;
			}
		}
		return true;
	}
}