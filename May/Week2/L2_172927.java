import java.util.*;

class Solution {
	public int solution(int[] picks, String[] minerals) {
		int answer = 0;

		List<int[]> mineralList = new ArrayList<>();

		int total = picks[0] + picks[1] + picks[2];

		int chunkCount = Math.min(minerals.length / 5 + (minerals.length % 5 == 0 ? 0 : 1), total);

		for(int i = 0; i < chunkCount; i++) {
			int[] chunk = new int[3];

			for(int j = i * 5; j < Math.min(i * 5 + 5, minerals.length); j++) {
				switch(minerals[j]) {
					case "diamond" : chunk[0]++; break;
					case "iron": chunk[1]++; break;
					case "stone": chunk[2]++; break;
				}
			}
			mineralList.add(chunk);
		}

		mineralList.sort((a, b) -> (b[0] * 25 + b[1] * 5 + b[2]) - (a[0] * 25 + a[1] * 5 + a[0]));

		for(int[] chunk : mineralList) {
			if(picks[0] > 0) {
				answer += chunk[0] + chunk[1] + chunk[2];
				picks[0]--;
			} else if(picks[1] > 0) {
				answer += chunk[0] * 5 + chunk[1] + chunk[2];
				picks[1]--;
			} else if(picks[2] > 0) {
				answer += chunk[0] + chunk[1] * 5 + chunk[2];
				picks[2]--;
			}
		}
		return answer;
	}
}