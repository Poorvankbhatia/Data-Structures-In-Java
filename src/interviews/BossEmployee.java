/*

At Flipkart, an employee has many subordinates. But an employee can also have many managers. 
And a manager can further have more managers
overseeing his work.
A simple "boss-employee-relationship-can be denoted by an arrow '-->'
A --> B --> C
This denotes that A is direct boss of B, who is direct boss of C. Here A is also (indirect) boss of C. 
It is guaranteed that there is no cycles in this relationship. That is there exists no pair of employees, X and Y, 
such that X is boss of Y and Y is also boss of X.
The salary of an employee can be calculated on the basis of following rules:
•an employee like C who has no subordinates earns 1.
•An employee who has direct subordinates earns a salary equal to the sum of his direct subordinates' salaries.

"relations" denotes a array of strings, where if the jthcharacter of ithstring is Y, 
if employee iis a direct boss of employee j. Otherwise it is "N". You have to display the sum of salaries of all employees.
Complete this function in the code editor
intSumming(String[] relations)
Constraints
The size of relations array will not have elements greater than 50.
the arrays will only contain string composed of "Y" & "N"
each element of the array has same number of characters
For kthrow. the kthelement will always be "N"
If X is boss of Y. Y cannot be a boss or X.
Test cases are designed such that answers will always lie within the range of signed 32bit int.
Sample Case #1
Input
Returns: 1
Explanation: There is only one employee, so his salary will be 1.
Sample Case #2
Input
NNYN
NNYN
NNNN
NYYN
Returns: 5
Explanation: It has the following relation.
  4
 \
1 | 2
\| /
3
6
So salary of 3rd employee is 1, 1st and 2nd employee is equal to the sum of its (only) employee 
(3rd) which is 1. Salary of 4th employee is sum of salary of 2nd and 3rd employee which is 1 + 1 2. 
So total salary is 2 + 1 + 1 +1 = 5.


 */

package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by poorvank on 4/26/15.
 */

class ENode {

    public int info;
    public List<Integer> childList;

    public ENode(int n) {
        info = n;
        childList = new ArrayList<>();
    }

}

class EGraph {

    public int count;
    public ENode[] array;

    public EGraph(int n) {

        count = n;
        array = new ENode[n];

        for (int i = 0; i < n; i++) {
            array[i] = new ENode(i);
        }

    }

    public void addEdge(int a, int b) {

        System.out.println("Creating edge between " + a + " - " + b);

        array[a].childList.add(b);

    }

    public int salarySum() {

        int[] salaryArray = new int[count];

        for (int i = 0; i < count; i++) {
            if (salaryArray[i] == 0) {
                fillSalarySum(salaryArray, i);
            }
        }

        int sum = 0;

        for (int i = 0; i < salaryArray.length; i++) {
            sum += salaryArray[i];
        }

        return sum;

    }

    private void fillSalarySum(int[] salaryArray, int i) {

        if (array[i].childList.size() == 0) {
            salaryArray[i] = 1;
            return;
        }

        for (Integer nbr : array[i].childList) {
            if (salaryArray[nbr] == 0) {
                fillSalarySum(salaryArray, nbr);
            }
            salaryArray[i] += salaryArray[nbr];
        }

    }


}

public class BossEmployee {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of input strings : ");
        int numberOfNodes = sc.nextInt();
        EGraph graph = new EGraph(numberOfNodes);

        sc.nextLine();
        for (int i = 0; i < numberOfNodes; i++) {
            String str = sc.nextLine();
            processInput(str, i, graph);
        }

        System.out.println("Salary sum is- " + graph.salarySum());


    }

    private static void processInput(String str, int currentNode, EGraph graph) {

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'Y') {
                graph.addEdge(currentNode, i);
            }
        }

    }

}


/*

Simple DFS approach used

 */