/*

A circus is designing a tower routine consisting of people standing atop one another’s shoulders.
For practical and aesthetic reasons, each person must be both shorter and lighter than the person below him or her.
Given the heights and weights of each person in the circus, write a method to compute the largest possible number of people in such a tower.

EXAMPLE:

Input:

(ht, wt): (65, 100) (70, 150) (56, 90) (75, 190) (60, 95) (68, 110)
Output:

The longest tower is length 6 and includes from top to bottom:

(56, 90) (60,95) (65,100) (68,110) (70,150) (75,190)

 */

package dyanamicprogramming.lis;

import java.util.Arrays;

/**
 * Created by poorvank on 05/07/16.
 */

class Person implements Comparable<Person> {
    int height;
    int weight;

    public Person(int height, int weight) {
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Person{" +
                "height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Person p) {
        if(this.height>p.height) {
            return 1;
        } else if(this.height < p.height) {
            return -1;
        } else {
            return 0;
        }
    }
}

public class CircusTower {

    public void calculatePeople(Person[] input) {

        int heightArray[] = new int[input.length];
        int weightArray[] = new int[input.length];
        String[] output = new String[input.length];
        for (int i=0;i<input.length;i++) {
            weightArray[i] = 1;
            output[i] = input[i].toString() + "";
        }
        int maxLength = 0;

        for (int i=1;i<input.length;i++) {
            for (int j=0;j<i;j++) {
                if( weightArray[j]+1>weightArray[i] && input[i].weight>input[j].weight) {
                    weightArray[i] = weightArray[j] + 1;
                    output[i] = output[j] + " " + input[i].toString();
                    if(maxLength<weightArray[i]) {
                        maxLength = weightArray[i];
                    }
                }
            }
        }

        System.out.println();


        for (int i = 0; i < input.length; i++) {
            if (weightArray[i] == maxLength) {
                System.out.println("Longest Increasing subsequence - " + output[i] + " of length = " + maxLength);
            }
        }

    }

    public static void main(String[] args) {
        CircusTower ct = new CircusTower();
        Person p1 = new Person(65,100);
        Person p2 = new Person(70,150);
        Person p3 = new Person(56, 90);
        Person p4 = new Person(75, 190);
        Person p5 = new Person(60, 95);
        Person p6 = new Person(68, 110);

        Person[] array = new Person[]{p1,p2,p3,p4,p5,p6};

        Arrays.sort(array);

        System.out.println(Arrays.toString(array));

        ct.calculatePeople(array);

    }

}
