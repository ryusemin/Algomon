import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        String S = br.readLine();
        
        if (N % 2 != 0) {
            System.out.println("No");
            return;
        }
        
        int cntO = 0; 
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == ')') cntO++;
        }
        
     
        int needO = N / 2 - cntO;  
        int check = 0; 
        
        for (int i = N - 1; i >= 0; i--) {
            if (S.charAt(i) == ')') {
                check--;
            } else if (S.charAt(i) == '(') {
                check++;
            } else { 
                if (needO > 0) {
                    check--;
                    needO--;
                } else {
                    check++;
                }
            }
            if (check > 0) {
                System.out.println("No");
                return;
            }
        }
        if (check == 0) System.out.println("Yes");
        else System.out.println("No");
    }
}
