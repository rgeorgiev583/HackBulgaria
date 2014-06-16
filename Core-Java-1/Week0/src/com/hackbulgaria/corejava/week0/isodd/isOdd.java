package com.hackbulgaria.corejava.week0.isodd;

public class isOdd
{
    public static boolean isOdd(int n)
    {
        return n % 2 != 0;
    }

    public static void main(String[] args)
    {
        System.out.println(isOdd(5));
        System.out.println(isOdd(2));
        System.out.println(isOdd(-4));
        System.out.println(isOdd(-3));
    }
}
