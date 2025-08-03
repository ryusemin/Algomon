import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++){
            Deque<String> stack = new ArrayDeque<>();
            String[] arr = br.readLine().split("");
            for(int i = 0 ; i < arr.length ; i++){
                String s = arr[i];
                if(s.equals("(")){
                    stack.push(s);
                }
                else if(s.equals(")")){
                    if(!stack.isEmpty() && stack.peek().equals("(")) {
                        stack.pop();
                    }
                    else{
                        stack.push(s);
                    }
                }
            }
            if(stack.isEmpty()){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
    }
}