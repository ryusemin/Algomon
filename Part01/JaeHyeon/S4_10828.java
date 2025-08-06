import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_10828 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Stack stack = new Stack();

        int N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            switch (st.nextToken()) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(stack.pop()).append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    sb.append(stack.empty() ? 1 : 0).append("\n");
                    break;
                case "top":
                    sb.append(stack.top()).append("\n");
                    break;
            }
        }

        System.out.println(sb.toString());
    }
}

class Stack {
    int top;
    int[] data;

    Stack() {
        top = 0;
        data = new int[100001];
    }

    void push(int x) {
        data[top++] = x;
    }

    int pop() {
        return empty() ? -1 : data[--top];
    }

    int size() {
        return top;
    }

    boolean empty() {
        return size() == 0;
    }

    int top() {
        return empty() ? -1 : data[top - 1];
    }
}