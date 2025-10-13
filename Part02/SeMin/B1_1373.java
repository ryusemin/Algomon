import java.io.*;
import java.util.*;

public class B1_1373 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] arr = {"000", "001", "010", "011", "100", "101", "110", "111"};
        String str = br.readLine();

        while (str.length() % 3 != 0) {
            str = "0".concat(str);
        }

        for (int i = 0; i < str.length(); i += 3) {
            String num = str.substring(i, i + 3);
            for (int j = 0; j < 8; j++) {
                if (arr[j].equals(num)) {
                    sb.append(j);
                    break;
                }
            }
        }

        System.out.println(sb.toString());
    }
}