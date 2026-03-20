import java.io.*;
import java.util.*;

import org.w3c.dom.Node;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		System.out.println(M - gcd(N, M));
	}

	static int gcd(int a, int b) {
		return a % b == 0 ? b : gcd(b, a % b);
	}
}