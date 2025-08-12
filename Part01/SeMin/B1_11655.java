import java.util.*;
import java.io.*;

class B1_11655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] words = br.readLine().split(" ");

        for(int i = 0; i < words.length; i++){
            String word = words[i];
            for(int j = 0; j< word.length(); j++){
                char c = word.charAt(j);
                char result = c;
                if(c >= 65 && c <= 90){
                    result += 13;
                    if(result > 90){
                        result -= 26;
                    }
                } else if (c >=97 && c <= 122) {
                    result += 13;
                    if(result > 122){
                        result -= 26;
                    }
                }
                System.out.print(result);
            }
            System.out.print(" ");
        }
    }
}