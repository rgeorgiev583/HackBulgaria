package com.hackbulgaria.corejava.tddintro.helloworld;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JUnitHelloWorldTestCase {
    private JUnitHelloWorld jUnitHelloWorld;
    
    @Before
    public void setUp() throws Exception {
        this.jUnitHelloWorld = new JUnitHelloWorld();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        assertEquals(1, jUnitHelloWorld.getNumberOfDigits(7));
    }
    
    @Test
    public void testMoreThanOneDigit() {
        assertEquals(2, jUnitHelloWorld.getNumberOfDigits(55));
    }
    
    @Test
    public void testNegatives() {
        assertEquals(3, jUnitHelloWorld.getNumberOfDigits(-767));
    }
    
    @Test
    public void testZero() {
        assertEquals(1, jUnitHelloWorld.getNumberOfDigits(0));
    }
}
