import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B4_10808 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        int[] arr = new int[26];

        for (int i = 0; i < str.length(); i++) {
            arr[str.charAt(i) - 'a']++;
        }

        for (int n : arr) {
            sb.append(n).append(" ");
        }

        System.out.println(sb.toString());
    }
}
