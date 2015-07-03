/*

A person wants to go from origin to a particular location, 
he can move in only 4 directions(i.eEast, West, North, South) 
but his friend gave him a long route, help a person to find minimum Moves so that he can reach to the destination.
Input –NESNWES
Output –E
You need to print the lexicographically sorted string. Assume the string will have only ‘E’ ‘N’ ‘S’ ‘W’ characters.
E.g. –SSSNEEEW
output –EESS

 */

package interviewProgram;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by poorvank on 4/26/15.
 */
public class Directions {

    public static void main(String[] args) {

        System.out.println("Enter Direction narrated by friend");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        HashMap<Character, Character> characterHashMap = new HashMap<>();

        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('N', 0);
        hashMap.put('E', 0);

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'N') {
                hashMap.put('N', hashMap.get('N') + 1);
            } else if (input.charAt(i) == 'E') {
                hashMap.put('E', hashMap.get('E') + 1);
            } else if (input.charAt(i) == 'W') {
                hashMap.put('E', hashMap.get('E') - 1);
            } else if (input.charAt(i) == 'S') {
                hashMap.put('N', hashMap.get('N') - 1);
            }
        }
        System.out.println(hashMap.toString());


        int e = hashMap.get('E');
        int n = hashMap.get('N');

        String result = "";
        char ch;
        if (e < 0) {
            ch = n > 0 ? 'N' : 'S';
            for (int i = 0; i < Math.abs(n); i++) {
                result += ch;
            }
            for (int i = 0; i < e; i++) {
                result += 'W';
            }
        } else {
            for (int i = 0; i < e; i++) {
                result += 'E';
            }
            ch = n > 0 ? 'N' : 'S';
            for (int i = 0; i < Math.abs(n); i++) {
                result += ch;
            }
        }

        System.out.println(result);

    }


}
