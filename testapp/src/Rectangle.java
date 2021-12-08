/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp;

import org.junit.*;
/**
 *
 * @author asteed
 */
public class Rectangle implements Shape {
    private final double base;
    private final double height;
    
    Rectangle(double [] sides) {
        checkForValidSides(sides);
        this.base = sides[0];
        this.height = sides[1];
    }

    private void checkForValidSides(double [] sides) {
        if (sides.length != 2) {
            throw new IllegalArgumentException("Invalid number of sides");
        }

        for (int i=0; i<sides.length; i++) {
            if (sides[i] <= 0) {
                throw new IllegalArgumentException("Invalid sides values");
            }
        }
    }
    
    @Override
    public double getCircumference() {
        return 2 * base + 2 * height;
    }

    @Override
    public double getArea() {
        return base * height;
    }
    
    public boolean isSquare() {
        return base == height;
    }
}
