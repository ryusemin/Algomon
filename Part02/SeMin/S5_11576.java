import java.io.*;
import java.util.*;

class S5_11576 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");
        int[] arr = new int[m];

        for(int i =0; i<m; i++){
            arr[i] = Integer.parseInt(s[i]);
        }

        int p = m - 1;
        int sum = 0;


        for(int i =0; i < m; i++){
            sum += arr[i] * (int)Math.pow( a, p-- );
        }

        List<Integer> list = new ArrayList<>();

        while(sum > 0){
            list.add(sum % b);
            sum /= b;
        }

        Collections.reverse(list);
        StringBuilder sb = new StringBuilder();

        for(int num : list){
            sb.append(num).append(" ");
        }

        System.out.println(sb);


    }
}