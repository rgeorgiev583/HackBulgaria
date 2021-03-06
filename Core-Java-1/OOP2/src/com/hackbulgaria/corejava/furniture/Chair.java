package com.hackbulgaria.corejava.furniture;

public class Chair extends Furniture {
	
	public Chair(Material material, int usedMaterial) {
		this.material = material;
		this.usedMaterial = usedMaterial;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return (int) Math.round((material.getPricePerKg() * usedMaterial + 5) * 1.2);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "A chair made of " + material.getName() + ".";
	}

}
