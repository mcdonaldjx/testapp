/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp;

/**
 *
 * @author asteed
 */
public class Triangle implements Shape {
    private final double a; //side a
    private final double b; //side b
    private final double c; //side c
    
    Triangle(double [] sides) {
        checkForValidSides(sides);
        a = sides[0];
        b = sides[1];
        c = sides[2];
    }
    
    private void checkForValidSides(double [] sides) {
        if (sides.length != 3) {
            throw new IllegalArgumentException("Invalid number of sides");
        }
        // Find sum of sides
        double sum = sides[0] + sides[1] + sides[2];
        for (int i=0; i<sides.length; i++) {
            if (sides[i] <= 0) {
                throw new IllegalArgumentException("Invalid sides values");
            }
            // Sum must be more than twice any one side
            if( sum <= 2*sides[i]) {
                throw new IllegalArgumentException("Invalid sides values");
            }
        }
    }

    @Override
    public double getCircumference() {
        return a + b + c;
    }

    @Override
    public double getArea() {
        // Use law of cosines to get angle C between a and b
        double C = Math.acos((a*a + b*b - c*c)/(2*a*b));
        
        // Calculate area using two sides and included angle
        return Math.sin(C)*a*b/2;
    }
    
    public boolean isEquilateralTriangle() {
        return (a == b && b == c);
    }
    
    public boolean isRightTriangle() {
        // Calculate angles A, B, C
        double A = Math.acos((b*b + c*c - a*a)/(2*b*c));
        double B = Math.acos((a*a + c*c - b*b)/(2*a*c));
        double C = Math.acos((a*a + b*b - c*c)/(2*a*b));
        
        return (A == Math.PI/2 || B == Math.PI/2 || C == Math.PI/2);
    }
    
    //New functions
    public boolean isIsoscelesTriangle() {
    	return (a == b ^ a == c ^ b == c);
    }
    public boolean isScaleneTriangle() {
    	return (a != b && a != c && b != c);
    }
    public boolean isAcuteAngledTriangle() {
    	 double A = Math.acos((b*b + c*c - a*a)/(2*b*c));
         double B = Math.acos((a*a + c*c - b*b)/(2*a*c));
         double C = Math.acos((a*a + b*b - c*c)/(2*a*b));
    	return (A < 90.0 && B < 90.0 && C < 90.0);
    }
    public boolean isObtuseAngledTriangle() {
    	 double A = Math.acos((b*b + c*c - a*a)/(2*b*c));
         double B = Math.acos((a*a + c*c - b*b)/(2*a*c));
         double C = Math.acos((a*a + b*b - c*c)/(2*a*b));
        return (A > 90.0 || B > 90.0 || C > 90.0);
    }
}
