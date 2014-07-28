package com.hackbulgaria.corejava.box;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box<T> {
	private T item;
	
	public Box() {
	}
	
	public Box(T item) {
		set(item);
	}
	
	public T get() {
		return item;
	}
	
	public void set(T item) {
		this.item = item;
	}
	
	public static void main(String[] args) {
		Box<Integer> boxInt = new Box<>();
		boxInt.set(3);
		System.out.println(boxInt.get());
		
		Box<String> boxStr = new Box<>("this example belongs to Oracle");
		System.out.println(boxStr.get());
		
		Box<List<String> > boxListStr = new Box<>();
		boxListStr.set(new ArrayList<String>());
		boxListStr.get().add("All");
		boxListStr.get().add("your");
		boxListStr.get().addAll(Arrays.asList("base", "are", "belong", "to", "us"));
		System.out.printf("%s %s %s %s %s %s %s.\n", boxListStr.get().toArray());
		boxListStr.set(Arrays.asList("Oh", "my", "fucking", "god"));
		System.out.printf("%s %s %s %s!\n", boxListStr.get().toArray());
	}
}
