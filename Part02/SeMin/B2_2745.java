import java.io.*;
import java.util.*;

class B2_2745 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        long answer = 0;
        int a = 0;

        for(int i = N.length() - 1; i >= 0; i--){
            int num;
            char c = N.charAt(i);
            if(c >= '0' && c<= '9'){
                num = c - '0';
            }
            else{
                num = c - 'A' + 10;
            }
            answer += (num * Math.pow(B, a++));
        }
        System.out.println(answer);

    }
}