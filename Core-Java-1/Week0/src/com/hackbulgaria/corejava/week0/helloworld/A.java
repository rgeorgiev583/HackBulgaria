package com.hackbulgaria.corejava.week0.helloworld;

public class A
{
    private static int staticK = 3;
    
    public static void main(String[] args)
    {
        A a = new A();
        int[] myIntArray = new int[] {1, 2, 3, 4, 56};
        
        int c = 5;
        System.out.println(meh(c));
        System.out.println(myIntArray[0]);
        System.out.println(c);
        System.out.println(myIntArray[2]);
        meh(myIntArray);
        System.out.println(myIntArray[2]);
    }
    
    public static String meh(int c)
    {
        c = 7;
        return "meh";
    }
    
    public static String meh(int[] array)
    {
        array[2] = 67;
        return null;
    }
}
