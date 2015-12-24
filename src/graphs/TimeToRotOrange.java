package graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 12/23/15.
 */

class Element {
    
    public int x,y;
    
}

public class TimeToRotOrange {
    
    public static void main(String[] args) {
        
        
        int[][] arr = new int[][]{ {2, 1, 0, 2, 1},
                                   {1, 0, 1, 2, 1},
                                   {1, 0, 0, 2, 1}};
        
        
        System.out.println(minimumTime(arr));
        
    }
    
    private static boolean isValid(int x,int y,int row,int col) {
        
        return (x>=0 && x<row && y>=0 && y<col);
        
    }
    
    private static int minimumTime(int[][] arr) {


        Queue<Element> queue = new LinkedList<>();
        int row = arr.length;
        int col = arr[0].length;

        
        for(int i=0;i<row;i++) {
            for (int j=0;j<col;j++) {
                if(arr[i][j] == 2) {
                    Element element = new Element();
                    element.x = i;
                    element.y = j;
                    queue.add(element);
                }
            }
        }
        
        System.out.println(queue.size());
        
        int ans =0;
        
        Element delimiter = new Element();
        delimiter.x = -1;
        delimiter.y = -1;
        
        
        queue.add(delimiter);
        
        for (int i=0;i<=queue.size();i++) {
            System.out.println(queue.peek().x + " " + queue.peek().y);
            queue.remove();
        }
        
        System.exit(0);
        
        while (!queue.isEmpty()) {

            boolean flag = false;
            
            while (queue.peek().x!=-1 && queue.peek().y!=-1) {

                Element current = queue.peek();

                if(isValid(current.x+1,current.y,row,col) && arr[current.x+1][current.y]==1) {

                    if(!flag) {
                        flag = true;
                        System.out.println(1);
                        ans++;
                    }

                    arr[current.x+1][current.y] = 2;
                    current.x++;
                    queue.add(current);
                    current.x--;

                }

                if(isValid(current.x,current.y+1,row,col) && arr[current.x][current.y+1]==1) {

                    if(!flag) {
                        flag = true;
                        System.out.println(2);
                        ans++;
                    }

                    arr[current.x][current.y+1] = 2;
                    current.y++;
                    queue.add(current);
                    current.y--;

                }

                if(isValid(current.x-1,current.y,row,col) && arr[current.x-1][current.y]==1) {

                    if(!flag) {
                        flag = true;
                        System.out.println(3);
                        ans++;
                    }

                    arr[current.x-1][current.y] = 2;
                    current.x--;
                    queue.add(current);
                    current.x++;

                }

                if(isValid(current.x,current.y-1,row,col) && arr[current.x][current.y-1]==1) {

                    if(!flag) {
                        flag = true;
                        System.out.println(4);
                        ans++;
                    }

                    arr[current.x+1][current.y] = 2;
                    current.y--;
                    queue.add(current);
                    current.y++;

                }

                System.out.println(current.x + " " + current.y);
                queue.remove();
                System.out.println(queue.peek().x + " " + queue.peek().y);
            }

            queue.remove();

            if(!queue.isEmpty()) {
                delimiter.x = -1;
                delimiter.y = -1;

                queue.add(delimiter);
            }
            
            
        }
        
        
        
        return ans;
        
    }
    
}
