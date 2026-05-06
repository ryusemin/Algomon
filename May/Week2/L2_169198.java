class Solution {
	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for(int i = 0; i < balls.length; i++) {
			int tX = balls[i][0];
			int tY = balls[i][1];

			int min = Integer.MAX_VALUE;

			if(!(startX > tX && startY == tY)) {
				min = Math.min(min, getDistance(startX - (tX * -1), startY - tY));
			}

			if(!(startX < tX && startY == tY)) {
				min = Math.min(min, getDistance(startX - (2 * m - tX), startY - tY));
			}

			if(!(startY > tY && startX == tX)) {
				min = Math.min(min, getDistance(startX - tX, startY - (tY * -1)));
			}

			if(!(startY < tY && startX == tX)) {
				min = Math.min(min, getDistance(startX - tX, startY - (2 * n - tY)));
			}

			answer[i] = min;
		}
		return answer;
	}

	int getDistance(int x, int y) {
		return (int) Math.pow(x, 2) + (int) Math.pow(y, 2);
	}
}