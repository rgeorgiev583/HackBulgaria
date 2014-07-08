package com.hackbulgaria.corejava.furniture;

public class Main {
	public static void main(String[] args) {
		Furniture c = new Chair(new Wood(), 20);
		Furniture t = new Table(new Iron(), 40);
		
		System.out.println(c.getDescription());
		System.out.println("Its price is: " + c.getPrice());
		System.out.println();
		
		System.out.println(t.getDescription());
		System.out.println("Its price is: " + t.getPrice());
		System.out.println();
	}
}