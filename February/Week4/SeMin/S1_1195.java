import java.io.*;
import java.util.*;

public class Main {
    public static String upperGear; 
    public static String lowerGear; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        upperGear = br.readLine();
        lowerGear = br.readLine();
        System.out.println(calMinWidth());
    }

    private static int calMinWidth(){
        int minWidth = upperGear.length() + lowerGear.length();

        for(int offset = -lowerGear.length(); offset <= upperGear.length(); offset++){
            boolean isValid = true;

            for(int i = 0; i < upperGear.length(); i++){

                int lowerIdx = i - offset;
                if(lowerIdx < 0 || lowerIdx >= lowerGear.length()) continue; 

                int sum = (lowerGear.charAt(lowerIdx) - '0') + (upperGear.charAt(i) - '0');
                if(sum > 3){ 
                    isValid = false;
                    break;
                }
            }

            if(isValid){
                int currentWidth = Math.max(offset + lowerGear.length(), upperGear.length()) - Math.min(offset, 0);
                minWidth = Math.min(minWidth, currentWidth);
            }
        }

        return minWidth;
    }
}