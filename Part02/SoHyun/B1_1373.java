import java.io.*;
import java.math.BigInteger;

public class B1_1373 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        BigInteger n = new BigInteger(s, 2);

        String result = n.toString(8);

        System.out.println(result);
    }
}