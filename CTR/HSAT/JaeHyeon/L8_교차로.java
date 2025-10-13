import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Car[] carList = new Car[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String w = st.nextToken();
            carList[i] = new Car(t, w.charAt(0) - 'A', i);
        }

        Arrays.sort(carList, (c1, c2) -> c1.in - c2.in);

        int time = (N > 0 ? carList[0].in : 0);
        int done = 0;
        int idx = 0;

        Queue<Car> q = new ArrayDeque<>();
        int[] pass = new int[N];
        Arrays.fill(pass, -1);

        while (done < N) {
            while (idx < N && carList[idx].in <= time) {
                q.add(carList[idx]);
                idx++;
            }

            if (!q.isEmpty()) {
                Car[] front = new Car[4];
                for (Car c : q) {
                    if (front[c.dir] == null)   front[c.dir] = c;
                }

                boolean[] take = new boolean[4];
                for (int d = 0; d < 4; d++) {
                    if (front[d] != null && front[(d + 3) % 4] == null) take[d] = true;
                }

                boolean removed = false;
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    Car c = q.poll();
                    if (front[c.dir] == c && take[c.dir]) {
                        pass[c.idx] = time;
                        done++;
                        removed = true;
                    } else {
                        q.add(c);
                    }
                }

                if (!removed) {
                    boolean deadlock = true;
                    for (int d = 0; d < 4; d++) {
                        if (front[d] == null) {
                            deadlock = false;
                            break;
                        }
                    }

                    if (deadlock)   break;
                    time++;
                } else {
                    time++;
                }

            } else {
                if (idx < N) {
                    time = Math.max(time + 1, carList[idx].in);
                } else {
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N - 1; i++) {
            sb.append(pass[i]).append('\n');
        }
        sb.append(pass[N - 1]);
        System.out.println(sb.toString());
    }
}

class Car {
    int in;
    int dir;
    int idx;
    Car (int in, int dir, int idx) {
        this.in = in;
        this.dir = dir;
        this.idx = idx;
    }
}