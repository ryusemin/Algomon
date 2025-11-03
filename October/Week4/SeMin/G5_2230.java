import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        int[] sequence = new int[N];
        
        for (int i = 0; i < N; i++ ) {
            sequence[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(sequence);
        int i = 0, j = 0;
        int answer = Integer.MAX_VALUE;

        while (i < N) {
            if (sequence[i] - sequence[j] < M) {
                i++;
                continue;
            }
 
            if (sequence[i] - sequence[j] == M) {
                answer = M;
                break;
            }
 
            answer = Math.min(answer, sequence[i] - sequence[j]);
            j++;
        }
        System.out.println(answer);
    }
}