import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();

        int tc = Integer.parseInt(br.readLine());

        for(int i = 0; i < tc; i++){
            String[] command = br.readLine().split(" ");
            int size = list.size();
            switch (command[0]){
                case "push":
                    list.add(Integer.parseInt(command[1]));
                    break;
                case "pop":
                    if(size == 0){
                        System.out.println("-1");
                        break;
                    }
                    System.out.println(list.remove(size-1));
                    break;
                case "size":
                    System.out.println(size);
                    break;
                case "empty":
                    System.out.println(size == 0 ? 1 : 0);
                    break;
                case "top":
                    if(size == 0){
                        System.out.println("-1");
                        break;
                    }
                    System.out.println(list.get(size - 1));
                    break;
            }
        }
    }
}