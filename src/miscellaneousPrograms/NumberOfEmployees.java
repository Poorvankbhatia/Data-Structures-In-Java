/*

Given a dictionary that contains mapping of employee and his manager as a number of (employee, manager) pairs like below.

{ "A", "C" },
{ "B", "C" },
{ "C", "F" },
{ "D", "E" },
{ "E", "F" },
{ "F", "F" } 

In this example C is manager of A, 
C is also manager of B, F is manager 
of C and so on.
Write a function to get no of employees under each manager in the hierarchy not just their direct reports. 
It may be assumed that an employee directly reports to only one manager. 
In the above dictionary the root node/ceo is listed as reporting to himself.

Output should be a Dictionary that contains following.

A - 0  
B - 0
C - 2
D - 0
E - 1
F - 5 

 */

package miscellaneousPrograms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank on 5/10/15.
 */
public class NumberOfEmployees {
    
    private static HashMap<String,Integer> result = new HashMap<>();
    
    public static void main(String[] args) {

        HashMap<String,String> mapDictionary = new HashMap<>();
        mapDictionary.put("A","C");
        mapDictionary.put("C","F");
        mapDictionary.put("D","E");
        mapDictionary.put("E","F");
        mapDictionary.put("F","F");
        mapDictionary.put("B","C");
        
        populateResult(mapDictionary);
        System.out.println(result.toString());
    }
    
    private static void populateResult(HashMap<String,String> mapDictionary) {
        
        HashMap<String,List<String>> managerEmpMap = new HashMap<>();
        
        for (Map.Entry<String,String> entrySet : mapDictionary.entrySet()) {
            
            String employee = entrySet.getKey();
            String manager = entrySet.getValue();
            
            if(!employee.equals(manager)) {
                List<String> empList = managerEmpMap.get(manager);
                
                if(empList==null) {
                    empList = new ArrayList<>();
                }
                
                empList.add(employee);
                
                managerEmpMap.put(manager,empList);
            }
            
        }
        
        for (String employee : mapDictionary.keySet()) {
            populateResultUtil(employee,managerEmpMap);
        }
        
    }
    
    private static int populateResultUtil(String manager,HashMap<String,List<String>> managerEmpMap) {
        
        int count = 0;
        if(!managerEmpMap.containsKey(manager)) {
            result.put(manager, 0);
            return 0;
        }
        else if(result.containsKey(manager)) {
            return result.get(manager);
        }
        else {
            count = managerEmpMap.get(manager).size();
            for (String emp : managerEmpMap.get(manager)) {
                count += populateResultUtil(emp, managerEmpMap);
            }
            result.put(manager,count);
        }
        
        return count;
    }
    
}


/*

 1. Create a reverse map with Manager->DirectReportingEmployee 
    combination. Off-course employee will be multiple so Value 
    in Map is List of strings.
        "C" --> "A", "B",
        "E" --> "D" 
        "F" --> "C", "E", "F"

 
2. Now use the given employee-manager map to iterate  and at 
   the same time use newly reverse map to find the count of 
   employees under manager.
   
   Let the map created in step 2 be 'mngrEmpMap' 
   Do following for every employee 'emp'.
     a) If 'emp' is not present in 'mngrEmpMap' 
          Count under 'emp' is 0 [Nobody reports to 'emp']
     b) If 'emp' is present in 'mngrEmpMap' 
          Use the list of direct reports from map 'mngrEmpMap'
          and recursively calculate number of total employees
          under 'emp'. 
A trick in step 2.b is to use memorization(Dynamic programming) while finding number of employees
 under a manager so that we don’t need to find number of employees again for any of the employees.
  
  In the below code populateResultUtil() is the recursive function that uses memoization
 to avoid re-computation of same results.

 */