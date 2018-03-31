/*

Given a string as input, return the list of all the patterns possible:


'1' : ['A', 'B', 'C'],
'2' : ['D', 'E'],
'12' : ['X']
'3' : ['P', 'Q']
Example if input is '123', then output should be [ADP, ADQ, AEP, AEQ, BDP, BDQ, BEP, BEQ, CDP, CDQ, CEP, CEQ, XP, XQ]


 */
package strings.print;

import java.util.*;

/**
 * Created by poorvank.b on 31/03/18.
 */
public class PrintPatterns {

    private static Map<Integer,List<Character>> map;
    public static void main(String[] args) {
        map = new HashMap<>();
        map.put(1, Arrays.asList('A','B','C'));
        map.put(2, Arrays.asList('D','E'));
        map.put(12, Arrays.asList('X'));
        map.put(3, Arrays.asList('P','Q'));

        String s = "123";
        System.out.println(getPatterns(s));

    }

    private static Set<String> getPatterns(String s) {

        if(s.length()==0) {
            return new HashSet<>();
        }

        Set<String> set = new HashSet<>();

        for (int i=0;i<s.length();i++) {
            if(map.containsKey(Integer.parseInt(s.substring(0,i+1)))) {
                String newS = s.substring(0,i+1);
                Set<String> nextSet = getPatterns(s.substring(i+1));
                for (Character c : map.get(Integer.parseInt(newS))) {
                    if(nextSet.isEmpty()) {
                        set.add(c+"");
                    } else {
                        for (String x : nextSet) {
                            set.add(c+x);
                        }
                    }
                }
            }
        }

        return set;

    }

}
