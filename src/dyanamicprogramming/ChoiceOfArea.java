/*

Consider a game, in which you have two types of powers, A and B and there are 3 types of Areas X, Y and Z. Every second you have to
switch between these areas, each area has specific properties by which your power A and power B increase or decrease. We need to keep choosing
areas in such a way that our survival time is maximized. Survival time ends when any of the powers, A or B reaches less than 0.
Examples:

Initial value of Power A = 20
Initial value of Power B = 8

Area X (3, 2) : If you step into Area X,
                A increases by 3,
                B increases by 2

Area Y (-5, -10) : If you step into Area Y,
                   A decreases by 5,
                   B decreases by 10

Area Z (-20, 5) : If you step into Area Z,
                  A decreases by 20,
                  B increases by 5

It is possible to choose any area in our first step.
We can survive at max 5 unit of time by following
these choice of areas :
X -> Z -> X -> Y -> X

 */
package dyanamicprogramming;

import java.util.HashMap;

/**
 * Created by poorvank on 04/12/16.
 */
public class ChoiceOfArea {

    private static class Area{

        private int changeA;
        private int changeB;

        public Area(int changeA, int changeB) {
            this.changeA = changeA;
            this.changeB = changeB;
        }
    }

    private static class Pair {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int maxSurvivalTime(int powerA,int powerB,Area a,Area b,Area c) {

        if(powerA<=0 || powerB<=0) {
            return 0;
        }
        //To store the how many seconds it survives when powerA and powerB reach some particular values & avoid recomputation
        HashMap<Pair,Integer> map = new HashMap<>();

        return Math.max(Math.max(maxSurvivalUtil(powerA,powerB,'a',a,b,c,map),maxSurvivalUtil(powerA,powerB,'b',a,b,c,map)),
                maxSurvivalUtil(powerA,powerB,'c',a,b,c,map));

    }

    private static int maxSurvivalUtil(int powerA,int powerB,char currentArea,Area a,Area b,Area c,HashMap<Pair,Integer> map) {

        if(powerA<=0 || powerB<=0) {
            return 0;
        }

        Pair currentPair = new Pair(powerA,powerB);

        if(map.containsKey(currentPair)) {
            return map.get(currentPair);
        }

        int val = 0;

        if(currentArea=='a') {
            val = 1 + Math.max(maxSurvivalUtil(powerA+b.changeA,powerB+b.changeB,'b',a,b,c,map),
                    maxSurvivalUtil(powerA+c.changeA,powerB+c.changeB,'c',a,b,c,map));
        }

        else if(currentArea=='b') {
            val = 1 + Math.max(maxSurvivalUtil(powerA+a.changeA,powerB+b.changeB,'a',a,b,c,map),
                    maxSurvivalUtil(powerA+c.changeA,powerB+c.changeB,'c',a,b,c,map));
        }

        else if(currentArea=='c') {
            val = 1 + Math.max(maxSurvivalUtil(powerA+b.changeA,powerB+c.changeB,'b',a,b,c,map),
                    maxSurvivalUtil(powerA+a.changeA,powerB+a.changeB,'a',a,b,c,map));
        }

        map.put(currentPair,val);

        return val;

    }

    public static void main(String[] args) {

        Area a = new Area(3,2);
        Area b = new Area(-5,-10);
        Area c = new Area(-20,5);

        int powerA = 20;
        int powerB = 8;
        System.out.println(maxSurvivalTime(powerA,powerB,a,b,c));

    }


}


/*

We have to choose between maximum from the 3 areas every time.
after each time unit we can go to any of the area but we will choose that area which ultimately leads to maximum survival time.
As recursion can lead to solving same subproblem many time, we will memoize the result on basis of power A and B,
 if we reach to same pair of power A and B, we won’t solve it again instead we will take the previously calculated result.

 */