package com.hackbulgaria.corejava.newinstance;

public class ClassUtils {
	public static Object newInstance(Class<?> cls) throws InstantiationException, IllegalAccessException {
		return cls.newInstance();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(newInstance(ClassUtils.class));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
