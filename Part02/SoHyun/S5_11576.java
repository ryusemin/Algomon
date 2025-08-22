import java.io.*;
import java.util.*;

public class S5_11576 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        // 10진수 변환
        int decimalNum = 0;
        for (int i = m; i > 0; i--) {
            int num = Integer.parseInt(st.nextToken());
            decimalNum += num * Math.pow(a, i-1);
        }

        if (decimalNum == 0) {
            sb.append(0);
        }

        // b진수 변환
        while (decimalNum != 0) {
            list.add(decimalNum % b);
            decimalNum /= b;
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i)).append(" ");
        }

        System.out.println(sb);

    }
}

