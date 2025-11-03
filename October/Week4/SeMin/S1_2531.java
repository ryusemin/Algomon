import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] Ndkc = br.readLine().split(" ");

        int N = Integer.parseInt(Ndkc[0]); // 접시의 수
        int d = Integer.parseInt(Ndkc[1]); // 초밥 가짓수
        int k = Integer.parseInt(Ndkc[2]); // 연속 접시 수
        int c = Integer.parseInt(Ndkc[3]); // 쿠폰 번호

        int[] sushi = new int[N];
        int[] eating = new int[d + 1];
        
        for(int i = 0; i < N; i++){
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        for(int i = 0; i < k; i++){
            if(eating[sushi[i]] == 0) count++;
            eating[sushi[i]]++;
        }

        int max = count;
        
        for(int i = 1; i <= N; i++) {
			if(max <= count) {
				if(eating[c] == 0) {
					max = count + 1;
				}
				else {
					max = count;
				}
			}
			
			int end = (i + k - 1) % N;
			if(eating[sushi[end]] == 0) {
				count++;
			}
			eating[sushi[end]]++;
			
			eating[sushi[i - 1]]--;
			if(eating[sushi[i - 1]] == 0) {
				count--;
			}
		}
        
        System.out.println(max);
	}
}