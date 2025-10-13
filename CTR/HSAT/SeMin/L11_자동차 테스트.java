import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        int n = Integer.parseInt(s[0]);
        int q = Integer.parseInt(s[1]);

        List<Integer> arr = new ArrayList<>();

        String[] a = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            arr.add(Integer.parseInt(a[i]));
        }

        Collections.sort(arr);

        for(int i = 0; i < q; i++){
            int m = Integer.parseInt(br.readLine());

            int answer = 0;

            int index = Collections.binarySearch(arr, m);
            if(index >= 0) answer = index * ((n - 1) - index); 

            System.out.println(answer);
        }
        
    }
}