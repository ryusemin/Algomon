import java.util.*;
import java.io.*;

public class B3_10824 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = st.nextToken();
        String b = st.nextToken();
        String c = st.nextToken();
        String d = st.nextToken();

        long ab = Long.parseLong(a+b);
        long cd = Long.parseLong(c+d);

        sb.append(ab+cd);
        System.out.println(sb);

    }
}

