import java.util.*;
import java.io.*;

class Main {
    static int N;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        backtracking("");
    }

    static void backtracking(String result){
        if(result.length() == N){
            System.out.println(result);
            System.exit(0);
        }
        else {
            for (int i = 1; i <= 3; i++) {
                if(isGoodSequence(result + i)){
                    backtracking(result + i);
                }
            }
        }
    }

    static boolean isGoodSequence(String s){
        int len = s.length() / 2;
        for (int i = 1; i <= len; i++) {
            String front = s.substring(s.length() - i);
            String end = s.substring(s.length() - 2 * i, s.length() - i);

            if(front.equals(end)) return false;
        }
        return true;
    }

    
}