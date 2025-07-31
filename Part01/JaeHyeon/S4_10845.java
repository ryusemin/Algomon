import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S4_10845 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        CustomQueue queue = new CustomQueue();

        int N = Integer.parseInt(br.readLine());
        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String cmd = st.nextToken();

            switch (cmd) {
                case "push":
                    queue.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(queue.pop()).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    sb.append(queue.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(queue.front()).append("\n");
                    break;
                case "back":
                    sb.append(queue.back()).append("\n");
                    break;
            }

        }
        System.out.println(sb.toString());
    }
}

class CustomQueue {
    int[] data;
    int front;
    int rear;

    CustomQueue() {
        data = new int[100001];
        front = rear = 0;
    }

    void push(int x) {
        data[rear++] = x;
    }

    int pop() {
        return isEmpty() ? -1 : data[front++];
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