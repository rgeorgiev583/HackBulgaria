package com.hackbulgaria.corejava.exam1.listmethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListMethods {
    public static List<Integer> sorted(List<Integer> list) {
        List<Integer> listCopy = new ArrayList<Integer>(list);
        Collections.sort(listCopy);
        return listCopy;
    }
    
    public static List<Integer> reversed(List<Integer> list) {
        List<Integer> listCopy = new ArrayList<Integer>(list);
        Collections.reverse(listCopy);
        return listCopy;
    }

    public static boolean isMonotonous(List<Integer> list) {
        return sorted(list).equals(list) || reversed(sorted(list)).equals(list);
    }
}
