import java.io.*;
import java.util.*;

class B2_1212 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        String eight[] = {"000", "001", "010", "011", "100", "101", "110", "111"};

        for (int i = 0; i < a.length(); i++) {
            int num = a.charAt(i) - '0';

            if (i == 0 && num < 4) {
                if (num == 0) {
                    System.out.print("0");
                    break;
                } else {
                    if (num == 1)
                        System.out.print("1");
                    if (num == 2)
                        System.out.print("10");
                    if (num == 3)
                        System.out.print("11");
                }
            } else {
                System.out.print(eight[num]);
            }
        }
        System.out.println();
    }


}