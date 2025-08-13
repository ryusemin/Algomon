import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B1_11655 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                sb.append((char)('A' + (ch - 'A' + 13) % 26));
            } else if (ch >= 'a' && ch <= 'z')  {
                sb.append((char)('a' + (ch - 'a' + 13) % 26));
            } else {
                sb.append(ch);
            }
        }

        System.out.println(sb.toString());
    }
}
