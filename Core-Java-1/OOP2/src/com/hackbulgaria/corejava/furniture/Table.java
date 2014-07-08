package com.hackbulgaria.corejava.furniture;

public class Table extends Furniture {
	
	public Table(Material material, int usedMaterial) {
		this.material = material;
		this.usedMaterial = usedMaterial;
	}
	
	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return (int) Math.round((material.getPricePerKg() * usedMaterial + 10) * 1.2);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "A table made of " + material.getName() + ".";
	}

}
