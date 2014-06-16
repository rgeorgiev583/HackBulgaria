package com.hackbulgaria.corejava.tddintro;

import java.util.Arrays;

public class MaximumScalar {
    public long maxScalarProduct(int a[], int b[]) {
        Arrays.sort(a);
        Arrays.sort(b);
        long sum = 0;
        for (int i = 0; i < b.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}
