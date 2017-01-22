package utility;

/**
 * Created by poorvank on 14/07/16.
 */
public class LongestCommonPrefix {

    public static  String value(String s1,String s2) {
        int N = Math.min(s1.length(),s2.length());
        for (int i=0;i<N;i++) {
            if(s1.charAt(i)!=s2.charAt(i)) {
                return s1.substring(0,i);
            }
        }
        return s1.substring(0,N);
    }

}
