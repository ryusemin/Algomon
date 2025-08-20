import java.io.*;

public class B2_1212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String su = br.readLine();
        String[] arr = {"000", "001", "010", "011", "100", "101", "110", "111"};

        for (int i = 0; i < su.length(); i++) {
            int num = su.charAt(i) - '0';

            if (i == 0 && num < 4) {
                if (num == 0) {
                    sb.append("0");
                    break;
                } else {
                    if (num == 1)
                        sb.append("1");
                    if (num == 2)
                        sb.append("10");
                    if (num == 3)
                        sb.append("11");
                }
            } else {
                sb.append(arr[num]);
            }
        }
        System.out.println(sb);

    }
}
