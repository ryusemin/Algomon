import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] arr = br.readLine().split("");
        List<String> list = new LinkedList<>(Arrays.asList(arr));
        int M = Integer.parseInt(br.readLine());

        int cursor = list.size();

        for(int tc = 0; tc < M; tc++){
            String[] command = br.readLine().split(" ");
            String cmd = command[0];
            switch (cmd){
                case "L":
                    cursor = cursor <= 0? 0 : cursor - 1;
                    break;
                case "D":
                    cursor = cursor >= list.size()? list.size() : cursor + 1;
                    break;
                case "B":
                    if(cursor == 0) break;
                    list.remove(--cursor);
                    break;
                case "P":
                    list.add(cursor++, command[1]);
                    break;
            }
        }
        for(String s : list){
            System.out.print(s);
        }

    }
}