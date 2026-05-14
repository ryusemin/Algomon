import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int k;
	static int[] durability;
	static boolean[] person;
	static int count = 0;
	static int time = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		durability = new int[n * 2];
		person = new boolean[n];

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n * 2; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}

		while(true) {
			time++;
			rotate();
			move();
			putPerson();
			if(check()) break;
		}

		System.out.println(time);
	}

	static void rotate() {
		int tmp = durability[2 * n - 1];
		for(int i = n * 2 - 1; i > 0; i--) {
			durability[i] = durability[i - 1];
		}
		durability[0] = tmp;

		for(int i = n - 1; i > 0; i--) {
			person[i] = person[i - 1];
		}
		person[0] = false;
		person[n - 1] = false;
	}

	static void move() {
		for(int i = n - 2; i >= 0; i--) {
			if(person[i] && !person[i + 1] && durability[i + 1] > 0) {
				person[i] = false;
				person[i + 1] = true;
				durability[i + 1]--;

				if(durability[i + 1] == 0) count++;
			}
		}

		person[n - 1] = false;
	}

	static void putPerson() {
		if(durability[0] > 0 && !person[0]) {
			person[0] = true;
			durability[0]--;
			if(durability[0] == 0) count++;
		}
	}

	static boolean check() {
		int checkCount = 0;
		for(int i = 0; i < n * 2; i++) {
			if(durability[i] == 0) checkCount++;
		}
		return checkCount >= k;
	}
}