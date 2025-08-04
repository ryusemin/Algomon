import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split("");
        Deque<String> deque = new ArrayDeque<>();
        boolean isTag = false;

        for(int i =0; i < arr.length; i++){
            String s = arr[i];

            if(s.equals("<")){
                isTag = true;
            }
            else if(s.equals(">")){
                deque.offer(s);
                while(!deque.isEmpty()){
                    System.out.print(deque.poll());
                }
                isTag = false;
                continue;
            }

            if(isTag){
                deque.offer(s);
            }
            else{
                if(!s.equals(" ")) deque.push(s);

                if(s.equals(" ") || i == arr.length - 1) {
                    while (!deque.isEmpty()) {
                        System.out.print(deque.pop());
                    }
                    System.out.print(" ");
                }
            }




        }


    }
}