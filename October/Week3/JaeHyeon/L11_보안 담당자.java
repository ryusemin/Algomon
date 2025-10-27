import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br;
    static int n;
    public static void main(String[] args) throws Exception {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        String s = br.readLine();

        if (n % 2 == 1) {
            System.out.println("No");
            return;
        }

        int left = 0; 
        int right = 0; 
        int qMark = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else if (ch == ')') {
                right++;
            } else {
                qMark++;
            }
        }

        int needLeft = n / 2 - left;
        int needRight = n / 2 - right;

        if (needLeft < 0 || needRight < 0 || needLeft + needRight != qMark)  {
            System.out.println("No");
            return;
        }

        char[] cArr = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (cArr[i] == '?') {
                if (needLeft > 0) {
                    cArr[i] = '(';
                    needLeft--;
                } else {
                    cArr[i] = ')';
                    needRight--;
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (cArr[i] == '(') {
                cnt--;
            } else {
                cnt++;
            }

            if (cnt > 0) {
                System.out.println("No");
                return;
            }
        }

        System.out.println(cnt == 0 ? "Yes" : "No");
    }
}