import java.util.*;
public class Main {
    static char[][] map;
    static boolean[] alphabet;
    static Map<Character, int[]> alMap;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = sc.next();
        String key = sc.next();

        map = new char[5][5];
        alMap = new HashMap<>();
        alphabet = new boolean[26];
        alphabet[9] = true; // j 제외

        List<Character> list = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!list.contains(ch)) {
                list.add(ch);
                alphabet[ch - 'A'] = true;
            }
        }

        for (int i = 0; i < list.size(); i++) {
            map[i / 5][i % 5] = list.get(i);
            alMap.put(list.get(i), new int[]{i / 5, i % 5});
        }

        for (int i = list.size(); i < 25; i++) {
            for (int j = 0; j < 26; j++) {
                if (!alphabet[j]) {
                    map[i / 5][i % 5] = (char) ('A' + j);
                    alMap.put((char) ('A' + j), new int[]{i / 5, i % 5});
                    alphabet[j] = true;
                    break;
                }
            }
        }

        List<String> strList = new ArrayList<>();
        for (int i = 0; i < msg.length(); i++) {
            String str = msg.substring(i, Math.min(i + 2, msg.length()));

            if (str.length() == 1) {
                str = str.charAt(0) + "X";
            } else if (str.charAt(0) == str.charAt(1)) {
                if (str.charAt(0) == 'X') {
                    str = str.charAt(0) + "Q";
                } else {
                    str = str.charAt(0) + "X";
                }
            } else {
                i++;
            }

            strList.add(str);
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strList) {
            int[] fm = alMap.get(str.charAt(0));
            int[] sm = alMap.get(str.charAt(1));
            if (fm[0] == sm[0]) {
                sb.append(map[fm[0]][(fm[1] + 1) % 5]).append(map[sm[0]][(sm[1] + 1) % 5]);
            } else if (fm[1] == sm[1]) {
                sb.append(map[(fm[0] + 1) % 5][fm[1]]).append(map[(sm[0] + 1) % 5][sm[1]]);
            } else {
                sb.append(map[fm[0]][sm[1]]).append(map[sm[0]][fm[1]]);
            }
        }

        System.out.println(sb.toString());
    }
}