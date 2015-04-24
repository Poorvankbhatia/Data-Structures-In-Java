package Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by poorvank on 4/23/15.
 */


class MachineHeapNode {

    int element;
    int listIndex;

}

class MachineMinHeap {

    public ArrayList<Integer> list = new ArrayList<>();
    public MachineHeapNode[] heapArray;

    public MachineMinHeap(int n) {
        heapArray = new MachineHeapNode[n];
    }

    public ArrayList<Integer> produceOutput(LinkedList<LinkedList<Integer>> mainList) {


        int finalSize = 0;

        for (int i=0;i<heapArray.length;i++) {
            heapArray[i] = new MachineHeapNode();
            heapArray[i].element = mainList.get(i).get(0);
            heapArray[i].listIndex = i;
            finalSize += mainList.get(i).size();
        }

        minHeap(heapArray);

        for (int i=0;i<finalSize;i++) {

            MachineHeapNode machineHeapNode = heapArray[0];
            list.add(machineHeapNode.element);

            int listIndex = machineHeapNode.listIndex;

            mainList.get(listIndex).removeFirst();

            if (mainList.get(listIndex).size()>0) {
                heapArray[0].element = mainList.get(listIndex).get(0);
            } else {
                heapArray[0].element = Integer.MAX_VALUE;
            }

            minHeap(heapArray);

        }

        return list;


    }

    private void minHeap(MachineHeapNode[] heapArray) {

        for (int i=heapArray.length/2;i>=0;i--) {
            restoreUp(heapArray, i);
        }

    }

    private void restoreUp(MachineHeapNode[] heapArray,int i) {

        int leftChild = 2*i+1;
        int rightChild = 2*i+2;
        MachineHeapNode node = heapArray[i];

        while (rightChild<heapArray.length) {

            if(heapArray[rightChild].element > node.element && heapArray[leftChild].element > node.element) {
                heapArray[i] = node;
                return;
            }
            else if(heapArray[rightChild].element<heapArray[leftChild].element) {
                heapArray[i] = heapArray[rightChild];
                i = rightChild;
            }
            else {
                heapArray[i] = heapArray[leftChild];
                i = leftChild;
            }

            rightChild = 2*i+1;
            leftChild = 2*i+2;

        }

        if(leftChild==heapArray.length-1 && heapArray[leftChild].element<node.element) {
            heapArray[i] = heapArray[leftChild];
            i = leftChild;
        }

        heapArray[i] = node;

    }


}

public class SortMachineNo {

    public static void main(String[] args) {

        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(30);
        list1.add(40);
        list1.add(50);

        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(35);
        list2.add(45);

        LinkedList<Integer> list3 = new LinkedList<>();
        list3.add(10);
        list3.add(60);
        list3.add(70);
        list3.add(80);
        list3.add(100);

        LinkedList<LinkedList<Integer>> mainList = new LinkedList<>();
        mainList.add(list1);
        mainList.add(list2);
        mainList.add(list3);

        int noOfMachines = 3;


        MachineMinHeap machineMinHeap = new MachineMinHeap(noOfMachines);

        System.out.println("Output is-  " + machineMinHeap.produceOutput(mainList));
    }

}
