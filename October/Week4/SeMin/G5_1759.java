import java.io.*;
import java.util.*;

public class Main {
	static int L,C;
	static String[] password, alpha;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] LC = br.readLine().split(" ");
		L = Integer.parseInt(LC[0]);	
		C = Integer.parseInt(LC[1]);
		
		password= new String[L];
		alpha = new String[C];
		
		alpha = br.readLine().split(" ");
		Arrays.sort(alpha);
		combin(0,0);
	}
	
	static void combin(int len, int start) {
		if(len >= L) {
			if(check(password)) {
				for(String p : password) {
					System.out.print(p);
				}
				System.out.println();
			}
			return;
		}
		
		for(int i = start; i < C; i++) {
			password[len] = alpha[i];
			combin(len+1, i+1);
		}
	}
    
	static boolean check (String[] pw) {
		int c = 0; 
		int v = 0; 
		for (int i = 0; i < password.length; i++) {
			if(password[i].equals("a") || password[i].equals("e") || password[i].equals("i") || password[i].equals("o") || password[i].equals("u")){
				v++;
            }
			else c++;
		}
		if(c >= 2 && v >= 1) return true; 
		else return false; 
	}
    
}