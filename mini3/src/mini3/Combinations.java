package mini3;

import java.util.Arrays;

public class Combinations
{

   public static int[][] getCombinations(int[] choices)
   {

      

        if(choices.length == 0) 
        {

            return null;

        }
        else if(choices.length == 1) 
        {

          

            int m1 = choices[0];
            int[][] arr1 = new int[m1][1];
            for(int i = 0; i < m1; i++) 
            {
                arr1[i][0] = i;
            }
            return arr1;

          

        }
        else 
        {

          

            int n1 = choices.length;
            int[] new_Choices = new int[n1-1];
            for(int i = 1; i < n1; i++) 
            {
                new_Choices[i-1] = choices[i];
            }

          

            int arr1[][] = getCombinations(new_Choices);
            int size = arr1.length;
            int new_Arr[][] = new int[size*choices[0]][n1];
            int k = 0;

            for(int i = 0; i < choices[0]; i++)
            {
                for(int[] row: arr1) 
                {
                    new_Arr[k][0] = i;
                    for(int j = 1; j < n1; j++) 
                    {
                        new_Arr[k][j] = row[j-1];
                    }

                    k += 1;

                }

            }
            return new_Arr;
        }

    }

    public static void main(String[] args) {

        int[] options1 = new int[3];
        options1[0] = 3 ;
        options1[1] = 2 ;
        options1[2] = 4;
        int [][] combos = getCombinations(options1);
        for(int[] row : combos) {

            System.out.println(Arrays.toString(row));

        }

      
    }

}