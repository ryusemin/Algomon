import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int l, c;
    static List<String> result = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] ch = new char[c];
        for (int i = 0; i < c; i++) {
            ch[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(ch);

        dfs(0, 0, 0, 0, new StringBuilder(), ch);

        for (String s : result) {
            System.out.println(s);
        }

    }

    static void dfs(int idx , int cnt, int VC, int CC, StringBuilder sb, char[] ch) {
        if (cnt == l) {
            if (VC >= 1 && CC >= 2) {
                result.add(sb.toString());
            }
            return;
        }
        if (idx == c) return;

        for (int i = idx; i < c; i++) {
            char c = ch[i];
            sb.append(c);
            if (isVowel(c)) {
                dfs(i + 1, cnt + 1, VC + 1, CC, sb, ch);
            } else {
                dfs(i + 1, cnt + 1, VC, CC + 1, sb, ch);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    static boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }

        return false;
    }
}
