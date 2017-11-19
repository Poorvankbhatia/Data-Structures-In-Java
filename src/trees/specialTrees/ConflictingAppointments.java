/*

Given n appointments, find all conflicting appointments.

Examples:

Input: appointments[] = { {1, 5} {3, 7}, {2, 6}, {10, 15}, {5, 6}, {4, 100}}
Output: Following are conflicting intervals
[3,7] Conflicts with [1,5]
[2,6] Conflicts with [1,5]
[5,6] Conflicts with [3,7]
[4,100] Conflicts with [1,5]

 */

package trees.specialTrees;

/**
 * Created by poorvank on 3/31/15.
 */

class Appointment {

    int low;
    int high;

    public Appointment(int low, int high) {
        this.high = high;
        this.low = low;
    }

    public String toString() {

        return "{" + this.low + "," + this.high + "}";
    }
}

class AppointmentNode {

    Appointment appointment;
    int max;
    AppointmentNode left;
    AppointmentNode right;

    public AppointmentNode(Appointment appointment) {

        this.appointment = appointment;
        max = appointment.high;
        left = null;
        right = null;

    }

}

class AppointmentTree {

    public AppointmentNode root;

    public AppointmentTree() {

        root = null;

    }

    private boolean doOverlap(Appointment a1, Appointment a2) {

        if (a1.low <= a2.high && a1.high >= a2.low) {
            return true;
        }

        return false;
    }

    public AppointmentNode insert(AppointmentNode node, Appointment a) {

        if (node == null) {
            return new AppointmentNode(a);
        }

        int low = node.appointment.low;

        //System.out.println(node.appointment.toString() + "--" + a.toString());

        if (a.low < low) {
            node.left = insert(node.left, a);
        } else if (a.low > low) {
            node.right = insert(node.right, a);
        }

        if (node.max < a.high) {
            node.max = a.high;
        }

        return node;
    }

    public Appointment search(AppointmentNode node, Appointment i) {

        if (node == null) {
            return null;
        }

        if (doOverlap(i, node.appointment)) {
            return node.appointment;
        }

        // If left child of root is present and max of left child is
        // greater than or equal to given interval, then i may
        // overlap with an interval is left subtree

        else if (node.left != null && i.low <= node.left.max) {
            return search(node.left, i);
        } else {
            return search(node.right, i);
        }


    }

    public void printConflicting(Appointment[] appointments) {

        AppointmentNode node = null;

        node = insert(node, appointments[0]);

        for (int i = 1; i < appointments.length; i++) {

            System.out.println("Checking for  - " + appointments[i].toString());

            Appointment a = search(node, appointments[i]);
            if (a != null) {
                System.out.println(appointments[i].toString() + " conflicts with " + a.toString());
            }

            node = insert(node, appointments[i]);
        }

    }
}

public class ConflictingAppointments {

    public static void main(String[] args) {

        Appointment intervals[] = new Appointment[]{new Appointment(47,50), new Appointment(33,41), new Appointment(39,45)};

        AppointmentTree tree = new AppointmentTree();

        tree.printConflicting(intervals);


    }

}


/*

1) Create an Interval Tree, initially with the first appointment.
2) Do following for all other appointments starting from the second one.
   a) Check if the current appointment conflicts with any of the existing
     appointments in Interval Tree.  If conflicts, then print the current
     appointment.  This step can be done O(Logn) time.
   b) Insert the current appointment in Interval Tree. This step also can
      be done O(Logn) time.

 */