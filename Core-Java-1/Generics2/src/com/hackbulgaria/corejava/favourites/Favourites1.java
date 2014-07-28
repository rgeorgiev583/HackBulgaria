package com.hackbulgaria.corejava.favourites;

import java.util.HashMap;
import java.util.Map;

public class Favourites1 {
	Map<Class, Object> map;

	public Favourites1() {
		map = new HashMap<Class, Object>();
	}
	
	public Object get(Class cls) {
		return map.get(cls);
	}
	
	public void add(Class cls, Object obj) {
		map.put(cls, obj);
	}
	
	public static void main(String[] args) {
		Favourites1 favourites = new Favourites1();
		favourites.add(String.class, "myString");
		favourites.add(Integer.class, 3);

		System.out.println(favourites.get(Integer.class));
		System.out.println(favourites.get(String.class));
	}
}
