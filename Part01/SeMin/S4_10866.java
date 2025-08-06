import java.util.*;
import java.io.*;

public class S4_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Deque deque = new Deque();

        int N = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < N; tc++){
            String[] arr = br.readLine().split(" ");
            String command = arr[0];

            switch (command){
                case "push_front":
                    deque.push_front(Integer.parseInt(arr[1]));
                    break;
                case "push_back":
                    deque.push_back(Integer.parseInt(arr[1]));
                    break;
                case "pop_front":
                    System.out.println(deque.pop_front());
                    break;
                case "pop_back":
                    System.out.println(deque.pop_back());
                    break;
                case "size":
                    System.out.println(deque.size());
                    break;
                case "empty":
                    System.out.println(deque.empty());
                    break;
                case "front":
                    System.out.println(deque.front());
                    break;
                case "back":
                    System.out.println(deque.back());
                    break;
            }

        }
    }
}

class Deque{
    private int front;
    private int end;
    private int data[];

    public Deque(){
        this.data = new int[30002];
        this.front = 15000;
        this.end = 15000;
    }

    public void push_front(int x){
        data[--front] = x;
    }

    public void push_back(int x){
        data[end++] = x;
    }

    public int pop_front(){
        int result = data[front] == 0? -1 : data[front];
        if(result == -1) return result;

        data[front++] = 0;
        return result;
    }

    public int pop_back(){
        int result = data[end - 1] == 0? -1 : data[end - 1];
        if(result == -1) return result;

        data[--end] = 0;
        return result;
    }

    public int size(){
        return end-front;
    }

    public int empty(){
        return end-front == 0? 1:0;
    }

    public int front(){
        return data[front]== 0? -1: data[front];
    }

    public int back(){
        return data[end-1] == 0? -1: data[end-1];
    }

}