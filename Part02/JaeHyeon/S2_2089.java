import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S2_2089 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.println(0);
            return;
        }

        while (n != 0) {
            int remainder = n % -2; // 나머지 계산
            n /= -2;                // 몫 계산

            if (remainder < 0) {    // 나머지가 음수일 경우
                remainder += 2;     // 나머지를 양수로 변환
                n += 1;             // 몫을 1 증가
            }
            sb.append(remainder);   // 나머지 결과에 추가
        }

        System.out.println(sb.reverse().toString());
    }
}
