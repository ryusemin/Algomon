import java.util.*;
import java.io.*;

class S4_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        CustomQueue queue = new CustomQueue();

        for(int tc = 0; tc < T; tc++){
            String[] command = br.readLine().split(" ");
            String cmd = command[0];
            switch (cmd){
                case "push":
                    queue.push(Integer.parseInt(command[1]));
                    break;
                case "pop":
                    System.out.println(queue.pop());
                    break;
                case "size":
                    System.out.println(queue.size());
                    break;
                case "empty":
                    System.out.println(queue.empty());
                    break;
                case "front":
                    System.out.println(queue.front());
                    break;
                case "back":
                    System.out.println(queue.back());
                    break;
            }

        }
    }
}

class CustomQueue{
    int front;
    int end;
    int[] data;

    public CustomQueue(){
        front = 0;
        end = 0;
        data = new int[10001];
    }

    public int push(Integer num){
        data[end++] = num;
        return num;
    }

    public int pop(){
        if(data[front] == 0) return -1;

        int res = data[front];
        data[front++] = 0;

        return res;
    }

    public int size(){
        return end - front;
    }

    public int empty(){
        return end == front? 1 : 0;
    }

    public int front(){
        return data[front] == 0? -1 : data[front];
    }

    public int back(){
        int num = end-1  < 0 ? 0 : end-1;
        return data[num] == 0? -1 : data[num];
    }

}