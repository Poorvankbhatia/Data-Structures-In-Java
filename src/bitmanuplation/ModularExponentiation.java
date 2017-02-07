package bitmanuplation;

/**
 * Created by poorvank on 21/01/17.
 */
public class ModularExponentiation {

    private int power(int x,int y,int p) {

        int result = 1;

        x = x % p;

        while (y>0) {

            if((y&1)==1) {
                result = (result*x)%p;
            }

            y = y>>1;
            x = (x*x)%p;

        }

        return result;

    }

    public static void main(String[] args) {
        System.out.print(new ModularExponentiation().power(3,5,13));
    }

}


/*

The problem with above solutions is, overflow may occur for large value of n or x.
Therefore, power is generally evaluated under modulo of a large number.

Below is the fundamental modular property that is used for efficiently computing power under modular arithmetic.

(a mod p) (b mod p) ≡  (ab) mod p

or equivalently

( (a mod p) (b mod p) ) mod p  =  (ab) mod p

For example a = 50,  b = 100, p = 13
50  mod 13  = 11
100 mod 13  = 9

11*9 ≡ 1500 mod 13
or
11*9 mod 13 = 1500 mod 13


Left Shift

x = x * 2^value (normal operation)

x << value (bit-wise operation)

x = x * 16 (which is the same as 2^4)

The left shift equivalent would be x = x << 4

Right Shift

x = x / 2^value (normal arithmetic operation)

x >> value (bit-wise operation)

x = x / 8 (which is the same as 2^3)

The right shift equivalent would be x = x >> 3

 */