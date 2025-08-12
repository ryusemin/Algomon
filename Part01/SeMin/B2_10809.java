import java.util.*;
import java.io.*;

class B2_10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();

        int[] arr = new int[26];
        Arrays.fill(arr, -1);

        for(int i = 0; i < 26; i++){
            for(int j= 0; j < word.length(); j++){
                char w = word.charAt(j);
                if( w - 'a' == i && arr[i] == -1) arr[i] = j;
            }
        }

        for(int i = 0; i< arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}