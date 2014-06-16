package com.hackbulgaria.corejava.oopintro.car;

public class VW extends Car {
    protected VW(String model) {
        super("VW", model, "VW");
    }
    
    public void setBrand(String brand) {
        System.out.println(String.format("Error: You cannot change ''brand'' for ''Car''s of type ''%s''. It's always `%s'.", this.brand, this.brand));
    }
    
    public void setEngine(String engine) {
        System.out.println(String.format("Error: You cannot change ''engine'' for ''Car''s of type ''%s''. It's always `%s'.", this.brand, this.engine));
    }
    
    public String toString() {
        return this.getClass().toString();
    }
}
