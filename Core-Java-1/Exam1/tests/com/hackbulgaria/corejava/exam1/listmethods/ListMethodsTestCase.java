package com.hackbulgaria.corejava.exam1.listmethods;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class ListMethodsTestCase {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(3, 5, -1, 0, 2, -6, 4);
        List<Integer> sortedList = new ArrayList<Integer>(list);
        Collections.sort(sortedList);
        assertEquals(sortedList, ListMethods.sorted(list));
        List<Integer> reversedList = new ArrayList<Integer>(list);
        Collections.reverse(reversedList);
        assertEquals(reversedList, ListMethods.reversed(list));
        assertEquals(false, ListMethods.isMonotonous(list));
        List<Integer> list1 = Arrays.asList(1, 2, 5, 7);
        assertEquals(true, ListMethods.isMonotonous(list1));
    }

}
