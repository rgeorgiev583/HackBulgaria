package com.hackbulgaria.corejava.furniture;

public abstract class Furniture {
	protected Material material;
	protected int usedMaterial;
	
	public Furniture() {
	}
	
	public abstract int getPrice();
	public abstract String getDescription();
}
