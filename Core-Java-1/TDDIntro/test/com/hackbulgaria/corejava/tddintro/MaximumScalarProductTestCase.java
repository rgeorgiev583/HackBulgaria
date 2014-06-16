package com.hackbulgaria.corejava.tddintro;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MaximumScalarProductTestCase {
    private MaximumScalar maximumScalarProduct;
    
    @Before
    public void setUp() throws Exception {
        this.maximumScalarProduct = new MaximumScalar();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{4, 3, 2, 1};
        
        assertEquals(30, maximumScalarProduct.maxScalarProduct(a, b));
    }

    @Test
    public void testWithNegatives() {
        int[] a = new int[]{-4, -3};
        int[] b = new int[]{-5, -3};
        
        assertEquals(29, maximumScalarProduct.maxScalarProduct(a, b));
    }
    
    @Test
    public void testWithZeros() {
        int[] a = new int[]{0, 4};
        int[] b = new int[]{2, -9};
        
        assertEquals(8, maximumScalarProduct.maxScalarProduct(a, b));
    }
}
