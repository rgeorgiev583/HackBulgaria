package com.hackbulgaria.corejava.oopintro.car;

public class Car {
    protected String brand, model, engine;
    
    protected Car(String brand, String model, String engine) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getModel() {
        return model;
    }
    
    public String getEngine() {
        return engine;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public void setEngine(String engine) {
        this.engine = engine;
    }
}
