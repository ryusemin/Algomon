import java.util.*;
import java.io.*;

public class Main {
    static int Q;

    // i번 개미집의 위치
    static List<Integer> anthillPositions = new ArrayList<>();
    // i번 개미집 삭제 여부
    static List<Boolean> anthillDeleted = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Q = Integer.parseInt(br.readLine());

        // 0번 = 여왕 개미집
        anthillPositions.add(0);
        anthillDeleted.add(false);

        for (int query = 0; query < Q; query++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            switch (command) {
                // 초기 개미집 생성
                case 100:
                    int N = Integer.parseInt(st.nextToken());
                    for (int i = 1; i <= N; i++) {
                        int x = Integer.parseInt(st.nextToken());

                        anthillPositions.add(x);
                        anthillDeleted.add(false);
                    }
                    break;
                // 새 개미집 추가
                case 200:
                    int p = Integer.parseInt(st.nextToken());
                    anthillPositions.add(p);
                    anthillDeleted.add(false);
                    break;
                // 개미집 삭제
                case 300:
                    int q = Integer.parseInt(st.nextToken());
                    anthillDeleted.set(q, true);
                    break;

                // 정찰
                case 400:
                    int r = Integer.parseInt(st.nextToken());

                    int left = 0;
                    int right = 1_000_000_000;

                    int answer = 0;

                    while (left <= right) {
                        int mid = (left + right) / 2;
                        int needed = 0;
                        int lastCovered = -1_000_000_000;
                        for (int i = 1; i < anthillPositions.size(); i++) {
                            // 삭제된 개미집 스킵
                            if (anthillDeleted.get(i)) continue;
                            int current = anthillPositions.get(i);

                            // 새 개미 필요
                            if (current - lastCovered > mid) {
                                lastCovered = current;
                                needed++;
                            }
                        }

                        // 가능
                        if (needed <= r) {
                            answer = mid;
                            right = mid - 1;
                        }
                        // 불가능
                        else left = mid + 1;
                    }
                    System.out.println(answer);
                    break;
            }
        }
    }
}