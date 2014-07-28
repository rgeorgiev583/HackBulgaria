package com.hackbulgaria.corejava.greaterthan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CollectionUtils {
	public static <T> Collection<? extends Comparable<T> > greaterThan(Collection<? extends Comparable<T> > collection, T pivot) {
		Collection<Comparable<T> > filteredCollection = new ArrayList<Comparable<T> >();
		
		for (Comparable<T> item : collection) {
			if (item.compareTo(pivot) == 1) {
				filteredCollection.add(item);
			}
		}
		
		return filteredCollection;
	}
	
	public static void main(String[] args) {
		Collection<Integer> all = Arrays.asList(1,2,3,4,5,6,7);
		Collection<Integer> filtered = (Collection<Integer>) greaterThan(all, 5); //6, 7
		System.out.println(filtered);
	}
}
