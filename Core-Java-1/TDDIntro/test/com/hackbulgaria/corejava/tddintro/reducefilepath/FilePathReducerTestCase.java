package com.hackbulgaria.corejava.tddintro.reducefilepath;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FilePathReducerTestCase {
    private FilePathReducer filePathReducer;
    
    @Before
    public void setUp() throws Exception {
        this.filePathReducer = new FilePathReducer();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        assertEquals("/home/radorado/code/hackbulgaria", filePathReducer.reduceFilePathWithRegex("/home//radorado/code/./hackbulgaria/week0/../"));
    }
}
