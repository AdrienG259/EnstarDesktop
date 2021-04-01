package common;

import java.util.StringTokenizer;

public class MainStringTest {
    public static void main(String[] args) {
        String string = "AAA-A-A";
        String[] splitted = string.split("-");
        StringTokenizer st = new StringTokenizer(string);
        for (String s:splitted){
            System.out.println(s);
        }
        while(st.hasMoreTokens()){
            String s = st.nextToken("-");
            System.out.println("TOKEN "+s);
        }
    }
}
