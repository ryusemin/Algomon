import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2_10809 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        String str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (arr[idx] == -1) {
                arr[idx] = i;
            }
        }

        for (int n : arr) {
            sb.append(n).append(" ");
        }

        System.out.println(sb.toString());
    }
}
