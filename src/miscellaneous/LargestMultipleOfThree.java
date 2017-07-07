package miscellaneous;

/**
 * Created by poorvank.b on 25/06/17.
 */
public class LargestMultipleOfThree {

    private static void countSort(int[] l) {
        int n = l.length;

        int output[] = new int[n];

        int count[] = new int[10];
        for (int i=0; i<10; ++i)
            count[i] = 0;

        for (int anArr : l) {
            ++count[anArr];
        }

        for (int i=1; i<=9; ++i)
            count[i] += count[i-1];

        for (int anArr : l) {
            output[count[anArr] - 1] = anArr;
            --count[anArr];
        }

        for (int i = 0; i<n; ++i)
            l[i] = output[i];
    }

    private static int getSum(int[] nums) {
        int sum= 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public static int answer(int[] l) {

        int sum = getSum(l);
        int n = l.length;
        countSort(l);
        if(sum%3==0) {
            long result =0;
            int val=0;
            for (int i=l.length-1;i>=0;i--) {
                result = result*10 + l[i];
                if(result>Integer.MAX_VALUE) {

                }
            }
            val = (int) result;
            return val;
        }

        String res = "";


        int remainder = sum % 3;

        if(remainder==1) {

            int[] rem2 = new int[2];
            rem2[0] = -1; rem2[1] = -1;

            
            for (int i = 0 ; i < n ; i++)
            {
                
                if (l[i]%3 == 1)
                {
                    res =  getResult(l, n, i,-1);
                    System.out.println(res);
                    return 0;
                }

                if (l[i]%3 == 2)
                {
                   
                    if (rem2[0] == -1)
                        rem2[0] = i;

                        
                    else if (rem2[1] == -1)
                        rem2[1] = i;
                }
            }

            if (rem2[0] != -1 && rem2[1] != -1)
            {
                res =  getResult(l, n, rem2[0], rem2[1]);
                System.out.println(res);
                return 0;
            }

        } else if (remainder == 2) {
            int[] rem1 = new int[2];
            rem1[0] = -1; rem1[1] = -1;


            for (int i = 0; i < n; i++)
            {

                if (l[i]%3 == 2)
                {
                    res =  getResult(l, n, i,-1);
                    System.out.println(res);
                    return 0;
                }

                if (l[i]%3 == 1)
                {

                    if (rem1[0] == -1)
                        rem1[0] = i;

                        // If second occurrence
                    else if (rem1[1] == -1)
                        rem1[1] = i;
                }
            }

            if (rem1[0] != -1 && rem1[1] != -1)
            {
                res =  getResult(l, n, rem1[0], rem1[1]);
                System.out.println(res);
                return 0;
            }
        }

        return 0;

    }

    private static String getResult(int[] l, int n, int ind1, int ind2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = n-1; i >=0; i--)
            if (i != ind1 && i != ind2)
                stringBuilder.append(l[i]);

        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        int[] l = new int[]{4,4, 3,9,1,4,6,2,1,1,4,6,7,7,8};
        int k = answer(l);
        System.out.println(k);
        System.out.print(Integer.MAX_VALUE);
    }

}
