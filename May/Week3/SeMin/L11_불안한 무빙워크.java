import java.util.*;
import java.io.*;

class Plate {
    int stability;
    boolean occupied;

    public Plate(int stability, boolean occupied) {
        this.stability = stability;
        this.occupied = occupied;
    }
}

public class Main {
    public static final int MAX_N = 100;
    
    public static int n, k;
    
    public static Plate[] u = new Plate[MAX_N];
    public static Plate[] d = new Plate[MAX_N];
    
    public static void shift() {
        Plate temp = u[n - 1];
        for(int i = n - 1; i >= 1; i--){
            u[i] = u[i - 1];
        }
        
        u[0] = d[n - 1];
        
        for(int i = n - 1; i >= 1; i--){
            d[i] = d[i - 1];
        }
        d[0] = temp;
    }
    
    public static boolean canGo(int idx) {
        if(idx == n) return true;
        
        int stability = u[idx].stability;
        boolean occupied = u[idx].occupied;
        
        return stability > 0 && !occupied; 
    }
    
    public static void move(int idx) {
        int currStability = u[idx].stability;
        u[idx] = new Plate(currStability, false);
        
        if(idx + 1 < n) {
            int nextStability = u[idx + 1].stability;
            u[idx + 1] = new Plate(nextStability - 1, true);
        }
    }
    
    public static void moveAll() {
        for(int i = n - 1; i >= 0; i--) {
            boolean occupied = u[i].occupied;

            if(occupied && canGo(i + 1)) move(i);
        }
    }
    
    public static void add() {
        int stability = u[0].stability;
        boolean occupied = u[0].occupied;
        if(stability > 0 && !occupied)
            u[0] = new Plate(stability - 1, true);
    }
    
    public static void simulate() {
        shift();
        moveAll();
        add();
        
        boolean occupied = u[n - 1].occupied;
        if(occupied) move(n - 1);
    }
    
    public static boolean done() {
        int unstableCnt = 0;
        for(int i = 0; i < n; i++) {
            int stability = u[i].stability;
            if(stability == 0) unstableCnt++;
        }
        for(int i = 0; i < n; i++) {
            int stability = d[i].stability;
            if(stability == 0) unstableCnt++;
        }
        return unstableCnt >= k;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int stability = Integer.parseInt(st.nextToken());
            u[i] = new Plate(stability, false);
        }

        for(int i = 0; i < n; i++) {
            int stability = Integer.parseInt(st.nextToken());
            d[i] = new Plate(stability, false);
        }
        
        int trial = 0;
        while(!done()) {
            simulate();
            trial++;
        }
        
        System.out.print(trial);
    }
}
