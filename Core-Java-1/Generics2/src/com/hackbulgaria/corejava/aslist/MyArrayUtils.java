package com.hackbulgaria.corejava.aslist;

import java.util.ArrayList;
import java.util.List;

public class MyArrayUtils {
	@SafeVarargs
	public static <T> List<T> asList(T... ts) {
		List<T> list = new ArrayList<T>();
		
		for (int i = 0; i < ts.length; i++) {
			list.add(ts[i]);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		List<Integer> ints = asList(1,2,3,4,5);
		System.out.println(ints);
		List<String> strings = asList("1","2","3","4","5");
		System.out.println(strings);

		//also, give an explanation to what is happening in the lines below :)
		List<?> whatIsThis = asList(null,null,null);
		System.out.println(whatIsThis);
		List<Object> hahaaah = asList(null,null,null);
		System.out.println(hahaaah);
		List<Integer> didNotExpectThisWTF = asList(null,null,null);
		System.out.println(didNotExpectThisWTF);
		List<Number> nowWhat = asList(null,null,null);
		System.out.println(nowWhat);
	}
}
