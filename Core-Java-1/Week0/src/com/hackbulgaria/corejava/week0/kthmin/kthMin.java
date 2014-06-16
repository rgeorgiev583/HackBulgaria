package com.hackbulgaria.corejava.week0.kthmin;

import java.util.Arrays;

public class kthMin
{
    public static int kthMin(int k, int[] array)
    {
        int[] array_copy = array.clone();
        Arrays.sort(array_copy);
    }
    
    public static void main(String[] args)
    {
        int[] array = [7, 5, 9, 3, 6, 1];
        System.out.println(kthMin(3, array));
    }
}

/*
public class kthMin {
    public static int kthMin(int k, int[] array)
    {
        int mins[] = new int[k];
        int minIndexes[] = new int[k];
        
        for (int i = 0; i < k; i++)
        {
            mins[i] = array[0];
            minIndexes[i] = 0;
            
            for (int j = 1; j < array.length; j++)
            {
                
                for (int l = 0; l < i; l++)
                    if (minIndexes[l] != j)
                        //
                    
            }
        }
    }
}
*/