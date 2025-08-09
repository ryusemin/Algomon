import java.util.*;
import java.io.*;

S3_1935 {
    public static void main(String[] args) throws IOException {
        char[] alphabet = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z'
        };


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        Map<Character, Integer> map = new HashMap<>();


        String arr = br.readLine();

        for(int i =0; i< T; i++){
            map.put(alphabet[i], Integer.parseInt(br.readLine()));
        }

        Deque<Double> stack = new ArrayDeque<>();

        double answer = 0.0;

        for(int i = 0; i < arr.length(); i++){
            char now = arr.charAt(i);
            double result = 0.0;
            double before = 0.0;
            double after  = 0.0;

            switch (now){
                case '+':
                    before = stack.pop();
                    after   = stack.pop();
                    result = after + before;
                    stack.push(result);
                    break;
                case '-':
                    before = stack.pop();
                    after   = stack.pop();
                    result = after - before;
                    stack.push(result);
                    break;
                case '/':
                    before = stack.pop();
                    after   = stack.pop();

                    result = after / before ;
                    stack.push(result);
                    break;
                case '*':
                    before = stack.pop();
                    after  = stack.pop();

                    result = before * after;
                    stack.push(result);
                    break;
                default:
                    result = map.get(now);
                    stack.push(result);
                    break;
            }
        }

        answer = stack.pop();
        System.out.printf("%.2f" ,answer);


    }
}