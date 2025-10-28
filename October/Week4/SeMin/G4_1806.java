import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ns = br.readLine().split(" ");
		
		int n = Integer.parseInt(ns[0]);
		int s = Integer.parseInt(ns[1]);
		
		int[] sequence = new int[n+1];
		String[] arr = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			sequence[i] = Integer.parseInt(arr[i]);
		}
		
		int start = 0, end = 0;
		int len = Integer.MAX_VALUE;
		int sum = 0;
        
		while(start <= end && end <= n) {
			if(sum < s) {
				sum += sequence[end++];
			} else if(sum >= s) {
				len = Math.min(len, end-start);
				sum -= sequence[start++];
			}  
		}
        
		System.out.println(len == Integer.MAX_VALUE ? 0 : len);
	}
}