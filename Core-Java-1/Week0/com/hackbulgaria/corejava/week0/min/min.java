package com.hackbulgaria.corejava.week0.min;

public class min
{
    public static int min(int... array)
    {
        int tmin = array[0];
        
        for (int i = 1; i < array.length; i++)
            if (array[i] < tmin)
                tmin = array[i];
            
        return tmin;
    }
    
    public static void main(String[] args)
    {
        System.out.println(min(new int[] {2,6,3,7,8,5}));
    }
}
