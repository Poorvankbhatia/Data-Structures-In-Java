import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by poorvank on 5/19/15.
 */
public class Numbers {
    
    public static void main(String[] args) {
        
        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/poorvak/Desktop/logs1.txt")));
            String line = bufferedReader.readLine();
            
            while (line!=null && !line.equals("")) {
                String[] elements = line.split("\\|");
                System.out.println(elements[0] + " " + elements[2]);
                line = bufferedReader.readLine();
            }
            
        } catch (Exception e) {
            System.out.println("Exception is - " +e);
        }
        
        
    }
    
}
