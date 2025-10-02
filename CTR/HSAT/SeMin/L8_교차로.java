import java.io.*;
import java.util.*;

public class Main {
	static class Car {
		int line;
		int time;
		Car(int line, int time) {
			this.line = line;
			this.time = time;
		}
	}

	static int N;
    static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
        arr = new int[N];
        Arrays.fill(arr, -1);

		Queue<Car>[] q = new Queue[4];
        // 각 뱡항 Queue 선언 (A, B, C, D)
		for (int i = 0; i < 4; i++){
			q[i] = new LinkedList();
        }
		for (int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split(" ");
            int dir = inputs[1].charAt(0) - 'A';
			q[dir].add(new Car(i, Integer.parseInt(inputs[0])));
		}

        int currentTime = -1;
        while(true){
           if(q[0].isEmpty() && q[1].isEmpty() && q[2].isEmpty() && q[3].isEmpty()) break;

            int[] state = new int[4];
            int minTime = Integer.MAX_VALUE;
            for(int i = 0; i < 4; i++){
                if(!q[i].isEmpty()){
                    int t = q[i].peek().time;
                    minTime = Math.min(t, minTime);
                    if(t <= currentTime) state[i] = 1;
                }
            }
            int count = 0;
            for(int value : state) count += value;          
            
            if(count == 0) currentTime = minTime; // 어떤 차량도 교차로에 진입하지 않은경우
            else if(count == 4) break; // 교착상태
            else{
                for(int i = 0; i < 4; i++){
                    // 현 도로의 차량이 대기중이고, 오른쪽 도로에 대기중인 차량이 없을 때 
                    if(state[i] != 0 && state[(i+3) % 4] == 0){
                        arr[q[i].poll().line] = currentTime;
                    }
                }
                currentTime++;
            }
        }
        
		for (int i = 0; i < N; i++){
			sb.append(arr[i]).append("\n");
        }

		System.out.println(sb);
	}
}