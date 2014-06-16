package com.hackbulgaria.corejava.oopintro.animal;


public class Dog implements Animal {
    public void makeSound() {
        System.out.println("Woof!");
    }

    public void eat() {
        System.out.println("OM NOM NOM NOM");
    }

    public void sleep() {
        System.out.println("Zzzzz...");
    }
}
