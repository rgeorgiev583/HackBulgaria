package com.hackbulgaria.corejava.tddintro.helloworld;

public class JUnitHelloWorld {
    public long getNumberOfDigits(int n)
    {
        int i = 0;
        
        if (n == 0) {
            return 1;
        }
        else {
            if (n < 0) {
                n = -n;
            }
            
            while (n > 0) {
                n /= 10;
                i++;
            }
            
            return i;
        }
    }
}
