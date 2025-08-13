import java.util.*;
import java.io.*;

class B2_10820 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        int[] arr;

        while ((str = br.readLine()) != null) {
            arr = new int[4];

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch >= 'a' && ch <= 'z') {
                    arr[0]++;
                }
                else if (ch >= 'A' && ch <= 'Z') {
                    arr[1]++;
                } else if (ch == ' ') {
                    arr[3]++;
                } else {
                    arr[2]++;
                }
            }

            for( int a : arr ){
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}