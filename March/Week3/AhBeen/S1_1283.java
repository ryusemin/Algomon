import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		boolean[] used = new boolean[26];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			int selectedIdx = -1;

			boolean isStart = true;
			for (int j = 0; j < line.length(); j++) {
				char ch = line.charAt(j);

				if (isStart && Character.isLetter(ch)) {
					int idx = Character.toLowerCase(ch) - 'a';
					if (!used[idx]) {
						used[idx] = true;
						selectedIdx = j;
						break;
					}
				}

				if (ch == ' ') isStart = true;
				else isStart = false;
			}

			if (selectedIdx == -1) {
				for (int j = 0; j < line.length(); j++) {
					char ch = line.charAt(j);

					if (!Character.isLetter(ch)) continue;

					int idx = Character.toLowerCase(ch) - 'a';
					if (!used[idx]) {
						used[idx] = true;
						selectedIdx = j;
						break;
					}
				}
			}

			if (selectedIdx == -1) {
				sb.append(line).append("\n");
			} else {
				sb.append(line, 0, selectedIdx)
					.append("[")
					.append(line.charAt(selectedIdx))
					.append("]")
					.append(line.substring(selectedIdx + 1))
					.append("\n");
			}
		}

		System.out.print(sb);
	}
}