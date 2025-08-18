import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_17087 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Math.abs(S - Integer.parseInt(st.nextToken()));
        }

        int temp = arr[0];
        for (int i = 1; i < N; i++) {
            temp = getGCD(temp, arr[i]);
        }

        System.out.println(temp);

    }

    private static int getGCD(int num1, int num2) {
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }

        while (num2 != 0) {
            int r = num1 % num2;
            num1 = num2;
            num2 = r;
        }

        return num1;
    }
}
