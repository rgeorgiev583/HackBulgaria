package com.hackbulgaria.corejava.oopintro.animal;


public class AnimalInteraction {
    public static void main(String args[]) {
        Dog dog = new Dog();
        
        interactWithAnimal(dog);
    }
    
    public static void interactWithAnimal(Animal animal) {
        animal.makeSound();
        animal.eat();
        animal.sleep();
    }
}
