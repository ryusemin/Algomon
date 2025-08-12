import java.util.*;
import java.io.*;

class B2_10808 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();

        for(int i = 0; i < 26; i++){
            char w;
            int count = 0;
            for(int j= 0; j < word.length(); j++){
                w = word.charAt(j);
                if( w - 'a' == i) count++;
            }

            System.out.print(count + " ");
        }
    }
}