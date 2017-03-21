package strings;

/*

No of changes to convert to one string to another. Final result both are anagrams

 */
public class AnagramDiff {

    static int diff(String a,String b) {

        int[] count = new int[26];

        if(a.length()==b.length()) {

            int result = 0;

            for (int i=0;i<a.length();i++) {
                count[a.charAt(i)-'a']++;
            }

            for (int i=0;i<b.length();i++) {
                if(count[b.charAt(i)-'a']>=1) {
                    count[b.charAt(i)-'a']--;
                }
            }

            for (int i=0;i<26;i++) {
                if(count[i]>0) {
                    result += count[i];
                }
            }

            return result;

        }
        return -1;
    }

    public static void main(String[] args) {
        String a = "hhpddlnnsjfoyxpci";
        String b = "ioigvjqzfbpllssuj";
        System.out.println(diff(a,b));
    }

}
