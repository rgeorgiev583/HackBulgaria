package com.hackbulgaria.corejava.tddintro.joinstring;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hackbulgaria.corejava.tddintro.joinstring.StringJoiner;

public class StringJoinerTestCase {
    private StringJoiner stringJoiner;
    
    @Before
    public void setUp() throws Exception {
        this.stringJoiner = new StringJoiner();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        assertEquals("what the frack", stringJoiner.stitchMeUp(" ", "what", "the", "frack"));
    }
    
    @Test
    public void testOne() {
        assertEquals("abc", stringJoiner.stitchMeUp(".", "abc"));
    }
    
    @Test
    public void testOneEmpty() {
        assertEquals("", stringJoiner.stitchMeUp(".", ""));
    }
    
    @Test
    public void testTwo() {
        assertEquals("abc,def", stringJoiner.stitchMeUp(",", "abc", "def"));
    }
    
    @Test
    public void testTwoEmpty() {
        assertEquals(",", stringJoiner.stitchMeUp(",", "", ""));
    }
}
