import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        int[] money = new int[n];
        
        for(int i = 0; i < n; i++){
            money[i] = Integer.parseInt(br.readLine());
        }
        int count = 0;
        int sum = 0;

        while(true){
            for (int i = n-1; i >= 0;i--) {
                if(sum + money[i] <= k){
                    sum += money[i];
                    count++;
                    break;
                }
            }

            if(sum == k) break;
        }
        System.out.println(count);

	}
}