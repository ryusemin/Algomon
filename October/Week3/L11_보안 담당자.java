import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		if(N % 2 == 1) {
			System.out.println("No");
			return;
		}
		String s = sc.nextLine();

		int close = 0;
		int open = 0;

		for (char c : s.toCharArray()) {
			if (c == '(') {
				close++;
				open++;
			} else if (c == ')') {
				close--;
				open--;
			} else {
				close--;
				open++;
			}

			if (open < 0) {
				System.out.println("No");
				return;
			}

			if (close < 0) {
				close = 0;
			}
		}

		if (close == 0) System.out.println("Yes");
		else System.out.println("No");
	}
}