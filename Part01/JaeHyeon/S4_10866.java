import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_10866 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        CustomDeque deque = new CustomDeque();

        int N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();

            switch (cmd) {
                case "push_front":
                    deque.push_front(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.push_back(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    sb.append(deque.pop_front()).append("\n");
                    break;
                case "pop_back":
                    sb.append(deque.pop_back()).append("\n");
                    break;
                case "size":
                    sb.append(deque.size()).append("\n");
                    break;
                case "empty":
                    sb.append(deque.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(deque.front()).append("\n");
                    break;
                case "back":
                    sb.append(deque.back()).append("\n");
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}

class CustomDeque {
    int front, rear;
    int[] data;

    CustomDeque() {
        front = rear = 10000;
        data = new int[20001];
    }

    void push_front(int x) {
        data[--front] = x;
    }

    void push_back(int x) {
        data[rear++] = x;
    }

    int pop_front() {
        return isEmpty() ? -1 : data[front++];
    }

    int pop_back() {
        return isEmpty() ? -1 : data[--rear];
    }

    int size() {
        return rear - front;
    }

    boolean isEmpty() {
        return rear == front;
    }

    int front() {
        return isEmpty() ? -1 : data[front];
    }

    int back() {
        return isEmpty() ? -1 : data[rear - 1];
    }
}