import java.util.*;
import java.io.*;
 
public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

        String[] s = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(s[i]);
		}
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine());
        int[] arrM = new int[M];
        String[] sm = br.readLine().split(" ");
        
        for(int i = 0; i < M; i++) {
			arrM[i] = Integer.parseInt(sm[i]);
		}
        
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			if(binarySearch(arr, arrM[i]) >= 0) {
				sb.append(1).append('\n');
			}
			else {
				sb.append(0).append('\n');
			}
		}
		System.out.println(sb);
	}
	
	
	public static int binarySearch(int[] arr, int key) {
		int lo = 0;			
		int hi = arr.length - 1;	
 
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
 
			if(key < arr[mid]) hi = mid - 1;
			else if(key > arr[mid]) lo = mid + 1;
			else return mid;
		}
		return -1;
 
	}
}