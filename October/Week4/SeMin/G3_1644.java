import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> prime_list = new ArrayList<>();
        boolean prime[] = new boolean[N + 1];
        
        prime[0] = prime[1] = true;  
        for(int i=2; i*i<=N; i++){
            if(!prime[i]){
                for(int j= i*i; j <= N; j+=i) {
                    prime[j] = true;
                }
            }
        }     

       for(int i=1; i<=N;i++){
        	if(!prime[i]) {
                prime_list.add(i);
            }
        }

        int start = 0, end = 0; 
        int sum = 0, count = 0;
        while(true) {
        	if(sum >= N ){
                sum -= prime_list.get(start++);
            }
            else if(end == prime_list.size()) break;
        	else {
                sum += prime_list.get(end++);
            }
            
        	if(N == sum) count++;        	
        }		
        System.out.println(count);	
        
	}
}