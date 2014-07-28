package com.hackbulgaria.corejava.newinstance;

public class ClassUtils {
	public static <T> T newInstance(Class<T> cls) throws InstantiationException, IllegalAccessException {
		return cls.newInstance();
	}
	
	public static void main(String[] args) {
		try {	
			System.out.println(newInstance(ClassUtils.class));
			System.out.println(newInstance((new Object() { public String hello() { return "Hello World!"; } }).getClass()).hello());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
