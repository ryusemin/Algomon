import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String stick = br.readLine();

        int answer = 0;
        boolean flag = false;

        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < stick.length(); i++) {
            char n = stick.charAt(i);

            if (n == ')') {
                if (!deque.isEmpty() && deque.peek() == '(') {
                    if (flag) {
                        deque.pop();
                        answer += deque.size();
                        flag = false;
                    }
                    else {
                        deque.pop();
                        answer++;
                        flag = false;
                    }
                }
            }
            else {
                deque.push(n);
                flag = true;
            }

        }
        System.out.println(answer);
    }
}





//import java.util.*;
//import java.io.*;
//
//class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String stick = br.readLine();
//
//        int answer = 0;
//
//        for(int i = 0; i< stick.length(); i++){
//            Stack <Character> deque = new Stack<>();
//
//            char n = stick.charAt(i);
//            if(n == ')') continue;
//            else deque.push(n);
//
//            int count = 0;
//            boolean first = true;
//            boolean flag = true;
//
//            for(int j = i+1; j< stick.length(); j++){
//                char c = stick.charAt(j);
//                if(c == ')') {
//                    if (!deque.isEmpty() && deque.peek() == '(') {
//                        if(first) break;
//                        else first = false;
//
//                        deque.pop();
//
//                        if(flag) {
//                            count++;
//                            flag = false;
//                        }
//
//                    } else if(deque.isEmpty() ||  deque.peek() == ')') break;
//                }
//                else {
//                    deque.push(c);
//                    first = false;
//                    flag  = true;
//                }
//
//                if(deque.isEmpty()) break;
//            }
//            answer += (count < 1)? 0 : count + 1;
//        }
//
//        System.out.println(answer);
//    }
//}