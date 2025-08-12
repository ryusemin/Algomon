import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class S4_11656 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        Set<String> set = new TreeSet<>();

        for (int i = 0; i < str.length(); i++) {
            set.add(str.substring(i));
        }

        for (String s : set) {
            sb.append(s).append("\n");
        }

        System.out.println(sb.toString());
    }
}
