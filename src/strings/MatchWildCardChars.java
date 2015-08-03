package strings;

/**
 * Created by poorvank on 7/10/15.
 */
public class MatchWildCardChars {

    public static void main(String[] args) {
        
        /*System.out.println(doesMatch("g*ks", "geeks", 0, 0));
        System.out.println(doesMatch("ge?ks*", "geeksforgeeks",0,0));
        System.out.println(doesMatch("*c*d", "abcd",0,0));
        System.out.println(doesMatch("*pqrs", "pqrst",0,0));
        System.out.println(doesMatch("abc*bcd", "abcdhghgbcd",0,0));
        System.out.println(doesMatch("abc*c?d", "abcd",0,0));*/
        System.out.println(doesMatch("*c*d", "abcd", 0, 0));
        /*System.out.println(doesMatch("*?c*d", "abcd", 0, 0));*/

    }

    private static boolean doesMatch(String s1, String s2, int i, int j) {

        if (i == s1.length() - 1 && j == s2.length() - 1) {
            return true;
        }

        System.out.println(i + " " + j);

        if (i < s1.length() && j < s2.length() && s1.charAt(i) == '*' && i + 1 != s1.length() && j == s2.length() - 1) {
            System.out.println("no");
            return false;
        }

        if (i < s1.length() && j < s2.length() && (s1.charAt(i) == '?' || s1.charAt(i) == s2.charAt(j))) {
            return doesMatch(s1, s2, i + 1, j + 1);
        }


        if (i < s1.length() && s1.charAt(i) == '*') {
            return doesMatch(s1, s2, i, j + 1) || doesMatch(s1, s2, i + 1, j);
        }
        System.out.println("na");

        return false;

    }

}

/*

http://www.geeksforgeeks.org/wildcard-character-matching/

 */
