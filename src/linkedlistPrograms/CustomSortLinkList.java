/*

Sort a linked list of 0s, 1s and 2s
Given a linked list of 0s, 1s and 2s, sort it.

 */

package linkedlistPrograms;

/**
 * Created by poorvank on 5/7/15.
 */
public class CustomSortLinkList {

    public static void main(String[] args) {

        LLNode head = Input.listInput();
        LinkedList.printList(head);
        sort(head);
        System.out.println();
        LinkedList.printList(head);

    }

    private static void sort(LLNode head) {

        int[] countArr = new int[]{0, 0, 0};

        LLNode tmp = head;

        while (tmp != null) {
            countArr[tmp.info]++;
            tmp = tmp.link;
        }

        tmp = head;

        int i = 0;

        while (tmp != null) {

            if (countArr[i] == 0) {
                i++;
            } else {
                tmp.info = i;
                tmp = tmp.link;
                countArr[i]--;
            }

        }

    }

}

/*

Following steps can be used to sort the given linked list.
1) Traverse the list and count the number of 0s, 1s and 2s. Let the counts be n1, n2 and n3 respectively.
2) Traverse the list again, fill the first n1 nodes with 0, then n2 nodes with 1 and finally n3 nodes with 2.

 */