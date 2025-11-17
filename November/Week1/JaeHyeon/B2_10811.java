import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N + 1];
		for (int a = 1; a <= N; a++) {
			arr[a] = a;
		}
		
		for (int a = 1; a <= M; a++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			
			while (i < j) {
				int temp = arr[i];
				arr[i++] = arr[j];
				arr[j--] = temp;
				
			}
			
		}
		for (int a = 1; a <= N; a++) {
			System.out.print(arr[a] + " ");
		}
		
		br.close();
		
	}

}