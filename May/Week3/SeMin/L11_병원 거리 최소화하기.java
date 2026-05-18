import java.util.*;
import java.io.*;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    public static int n, m;
    public static int minDistance = Integer.MAX_VALUE;
    public static List<Pair> people = new ArrayList<>();
    public static List<Pair> hospitals = new ArrayList<>();
    public static boolean[] visited;

    public static int getDistance() {
        int distance = 0;
    
        for(int i = 0; i < people.size(); i++) {
            int singleMin = Integer.MAX_VALUE;
            for(int j = 0; j < hospitals.size(); j++) {
                if(visited[j]) {
                    Pair p = people.get(i);
                    Pair h = hospitals.get(j);
                    int dist = Math.abs(p.x - h.x) + Math.abs(p.y - h.y);
                    singleMin = Math.min(singleMin, dist);
                }
            }
            distance += singleMin;
        }
    
        return distance;
    }
    
    public static void searchMinDistance(int cnt, int lastIdx) {
        if(cnt == m)  {
            minDistance = Math.min(minDistance, getDistance());
            return;
        }
    
        for(int i = lastIdx + 1; i < hospitals.size(); i++) { 
            visited[i] = true;
            searchMinDistance(cnt + 1, i);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());

                if(num == 1) people.add(new Pair(i, j));
                if(num == 2) hospitals.add(new Pair(i, j));
            }
        }
        visited = new boolean[hospitals.size()];

        searchMinDistance(0, -1);
        System.out.print(minDistance);
    }
}
 