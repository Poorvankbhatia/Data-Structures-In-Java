package miscellaneous;

import java.util.Scanner;

/**
 * Created by poorvank on 12/27/15.
 */
public class TinyUrl {

    static int k = 62;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter table number");
        int n = scanner.nextInt();

        String s = shortUrl(n);

        System.out.println("Short url - " + s);

        int x = tableNo(s);

        System.out.println("table number for " + s + " = " + x);


    }


    private static String shortUrl(int tableNo) {

        String map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder shortUrl = new StringBuilder();

        while (tableNo != 0) {

            int x = tableNo % k;
            shortUrl.append(map.charAt(x));
            tableNo = tableNo / k;

        }

        return shortUrl.reverse().toString();


    }

    private static int tableNo(String s) {

        int id = 0;

        for (int i = 0; i < s.length(); i++) {

            if ('a' <= s.charAt(i) && 'z' >= s.charAt(i)) {
                id = id * k + s.charAt(i) - 'a';
            }
            if ('A' <= s.charAt(i) && 'Z' >= s.charAt(i)) {
                id = id * k + s.charAt(i) - 'A' + 26;
            }
            if ('0' <= s.charAt(i) && '9' >= s.charAt(i)) {
                id = id * k + s.charAt(i) - '0' + 52;
            }
        }

        return id;
    }

}

/*


Theoretical background
You need a Bijective Function f. This is necessary so that you can find a inverse function g('abc') =
 123 for your f(123) = 'abc' function. This means:

There must be no x1, x2 (with x1 ≠ x2) that will make f(x1) = f(x2),
and for every y you must be able to find an x so that f(x) = y.
How to convert the ID to a shortened URL
Think of an alphabet we want to use. In your case that's [a-zA-Z0-9]. It contains 62 letters.
Take an auto-generated, unique numerical key (the auto-incremented id of a MySQL table for example).

For this example I will use 12510 (125 with a base of 10).

Now you have to convert 12510 to X62 (base 62).

12510 = 2×621 + 1×620 = [2,1]

This requires use of integer division and modulo. A pseudo-code example:

digits = []

while num > 0
  remainder = modulo(num, 62)
  digits.push(remainder)
  num = divide(num, 62)

digits = digits.reverse
Now map the indices 2 and 1 to your alphabet. This is how your mapping (with an array for example) could look like:

0  → a
1  → b
...
25 → z
...
52 → 0
61 → 9
With 2 → c and 1 → b you will receive cb62 as the shortened URL.

http://shor.ty/cb
How to resolve a shortened URL to the initial ID
The reverse is even easier. You just do a reverse lookup in your alphabet.

e9a62 will be resolved to "4th, 61st, and 0th letter in alphabet".

e9a62 = [4,61,0] = 4×622 + 61×621 + 0×620 = 1915810

Now find your database-record with WHERE id = 19158 and do the redirect.


A URL character can be one of the following
1) A lower case alphabet [‘a’ to ‘z’], total 26 characters
2) An upper case alphabet [‘A’ to ‘Z’], total 26 characters
3) A digit [‘0′ to ‘9’], total 10 characters

There are total 26 + 26 + 10 = 62 possible characters.

So the task is to convert a decimal number to base 62 number.

To get the original long url, we need to get url id in database. 
The id can be obtained using base 62 to decimal conversion.

7465182

 */