import java.io.*;
import java.util.*;

class B1_11005 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int a = 0;

        StringBuilder sb = new StringBuilder();

        while(N != 0){
            int num = N % B;

            if(num < 10) {
                sb.append((char)(num + '0'));
            }
            else{
                sb.append((char)(num - 10 + 'A'));
            }
            N /= B;
        }

        System.out.println(sb.reverse().toString());

    }
}