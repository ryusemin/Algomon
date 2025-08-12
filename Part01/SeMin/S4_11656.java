import java.util.*;
import java.io.*;

class S4_11656 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();

        String[] arr = new String[word.length()];

        for(int i = 0; i < word.length(); i++){
            arr[i] = word.substring(i);
        }
        Arrays.sort(arr);

        for(String a : arr){
            System.out.println(a);
        }
    }
}