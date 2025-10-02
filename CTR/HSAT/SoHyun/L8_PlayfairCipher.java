import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = br.readLine();
        String key = br.readLine();

        char[][] arr = new char[5][5];

        List<Character> alphabetList = new ArrayList<>();
        for (int i=0; i<26; i++) {
            if ((char) ('A'+i) == 'J') {
                continue;
            }
            alphabetList.add((char)('A'+i));
        }

        int r = 0;
        int c = 0;

        for (int i = 0; i < key.length(); i++) {
            char alphabet = key.charAt(i);
            if (alphabetList.contains(alphabet)) {
                arr[r][c] = alphabet;
                alphabetList.remove(Character.valueOf(alphabet));
                c++;
                if (c % 5 == 0) {
                    c = 0;
                    r++;
                }
            }
        }

        for (int i = 0; i < alphabetList.size(); i++) {
            arr[r][c] = alphabetList.get(i);
            c++;
            if (c % 5 == 0) {
                c = 0;
                r++;
            }
        }

        List<String> strList = new ArrayList<>();

        for(int i = 0; i < msg.length(); i+=2) {

            if (i == msg.length() -1 ) {
                strList.add(msg.charAt(i) + "X");
                continue;
            }

            if (msg.charAt(i) == msg.charAt(i+1)) {
                if (msg.charAt(i) == 'X') {
                    strList.add("XQ");
                    i--;
                    continue;
                } else {
                    strList.add(msg.charAt(i) + "X");
                    i--;
                    continue;
                }
            } else {
                strList.add(msg.substring(i,i+2));
            }
        }

        StringBuilder answer = new StringBuilder();

        for (String str : strList) {
            char first = str.charAt(0);
            char second = str.charAt(1);

            int nr1 = 0;
            int nc1 = 0;
            int nr2 = 0;
            int nc2 = 0;

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (arr[i][j] == first) {
                        nr1 = i;
                        nc1 = j;
                    }

                    if (arr[i][j] == second) {
                        nr2 = i;
                        nc2 = j;
                    }
                }
            }

            if (nr1 == nr2) {
                if (nc1 == 4) {
                    nc1 = 0;
                } else {
                    nc1++;
                }

                if (nc2 == 4) {
                    nc2 = 0;
                } else {
                    nc2++;
                }
                answer.append(arr[nr1][nc1] + "" + arr[nr2][nc2]);
            }

            else if (nc1 == nc2) {
                if (nr1 == 4) {
                    nr1 = 0;
                } else {
                    nr1++;
                }

                if (nr2 == 4) {
                    nr2 = 0;
                } else {
                    nr2++;
                }
                answer.append(arr[nr1][nc1] + "" + arr[nr2][nc2]);
            }

            else {
                int temp = nc1;
                nc1 = nc2;
                nc2 = temp;

                answer.append(arr[nr1][nc1] + "" + arr[nr2][nc2]);
            }
        }


        System.out.println(answer);

    }
}