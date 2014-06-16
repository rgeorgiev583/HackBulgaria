package com.hackbulgaria.corejava.oopintro.car;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CarTestCase {
    private Car car;
    private Audi audi;
    private BMW bmw;
    private VW vw;
    private Mercedes mercedes;
    
    @Before
    public void setUp() throws Exception {
        this.audi = new Audi("A8");
        this.bmw = new BMW("Z");
        this.vw = new VW("Golf");
        this.mercedes = new Mercedes("E300");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAudi() {
        car = new Car("Audi", "A8", "VW");
        System.out.println(audi.toString());
        assertEquals(car.brand, audi.brand);
        assertEquals(car.model, audi.model);
        assertEquals(car.engine, audi.engine);
    }

    @Test
    public void testBMW() {
        car = new Car("BMW", "Z", "BMW");
        System.out.println(bmw.toString());
        assertEquals(car.brand, bmw.brand);
        assertEquals(car.model, bmw.model);
        assertEquals(car.engine, bmw.engine);
    }
    
    @Test
    public void testVW() {
        car = new Car("VW", "Golf", "VW");
        System.out.println(vw.toString());
        assertEquals(car.brand, vw.brand);
        assertEquals(car.model, vw.model);
        assertEquals(car.engine, vw.engine);
    }
    
    @Test
    public void testMercedes() {
        car = new Car("Mercedes", "E300", "Mercedes");
        System.out.println(mercedes.toString());
        assertEquals(car.brand, mercedes.brand);
        assertEquals(car.model, mercedes.model);
        assertEquals(car.engine, mercedes.engine);
    }
}
