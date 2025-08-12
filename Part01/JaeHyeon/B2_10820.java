import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B2_10820 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str;
        int[] arr;

        while((str = br.readLine()) != null) {
            arr = new int[4];

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    arr[1]++;
                } else if (ch >= 'a' && ch <= 'z') {
                    arr[0]++;
                } else if (ch == ' ') {
                    arr[3]++;
                } else {
                    arr[2]++;
                }
            }

            for (int n : arr) {
                sb.append(n).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());

    }
}
