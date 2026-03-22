import java.util.*;
import java.io.*;

class Main {
    static Set<Character> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        // 옵션의 개수
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            boolean first = false;
            String option = br.readLine();
			sb.append(option);

            st = new StringTokenizer(option);

            // 1. 첫 글자가 단축키로 지정되었는지 
            while (st.hasMoreTokens()) {
                String s = st.nextToken();
                char check = s.charAt(0);
                if(!set.contains(check)){
                    addSet(check);
                    
                    int idx = option.indexOf(s); // option에서 해당 단어의 시작 인덱스
                    sb.insert(idx + 1, "]");
                    sb.insert(idx, "[");
                    first = true;
                    break;
                }
            }
            if(first) {
                System.out.println(sb);
                sb.setLength(0);
                continue;
            }
            st = new StringTokenizer(option);
            
            // 2. 왼쪽부터 차례대로 알파벳 보며
            for (int j = 0; j < option.length(); j++) {
                char check = option.charAt(j);
                if (check == ' ') continue; // 공백 스킵
                if (j == 0) continue;       // 첫 글자는 1번에서 이미 처리
                if (!set.contains(check)) {
                    addSet(check);

					sb.insert(j + 1, "]");
                	sb.insert(j, "[");
					break;
                }
            }

            System.out.println(sb);
            sb.setLength(0);
        }
        
    }

    // 단축키 지정 (Set)
    static void addSet(char c){
        set.add(Character.toLowerCase(c));
        set.add(Character.toUpperCase(c));
    }
}