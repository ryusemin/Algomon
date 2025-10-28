import java.util.*;
import java.io.*;
public class Main {
	static List<Integer> arr = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		if(n == 1) {
			System.out.println(0);
			return;
		}
		getPrime(n);

		int i = 0;
		int j = 0;
		int sum = 0;
		int count = 0;
		while(true) {
			if(sum >= n) {
				if(sum == n) {
					count++;
				}
				sum-= arr.get(i++);
			} else {
				if(j == arr.size()) {
					break;
				}
				sum += arr.get(j++);
			}
		}
		System.out.println(count);
	}

	static void getPrime(int n) {
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = false;
		isPrime[1] = false;
		for(int i = 2; i * i <= n; i++) {
			if(isPrime[i]) {
				for(int j = i * i; j <= n; j+= i) {
					isPrime[j] = false;
				}
			}
		}
		for(int i = 1; i <= n; i++) {
			if(isPrime[i]) {
				arr.add(i);
			}
		}
		arr.add(0);
	}
}