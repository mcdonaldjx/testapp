/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testapp;
import org.junit.*;


public class Ellipse implements Shape {
    
    private final double semiMajor;
    private final double semiMinor;
    
    Ellipse(double [] axes) {
        checkForValidAxes(axes);
        if (axes[0] > axes[1]) {
            semiMajor = axes[0];
            semiMinor = axes[1];
        }
        else {
            semiMajor = axes[1];
            semiMinor = axes[0];            
        }
    }
    
    private void checkForValidAxes(double [] axes) {
        if (axes.length != 2) {
            throw new IllegalArgumentException("Invalid number of axes");            
        }
        for (int i=0; i<axes.length; i++) {
            if (axes[i] <= 0) {
                throw new IllegalArgumentException("Invalid axis values");
            }
        }
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * Math.sqrt((semiMajor * semiMajor + semiMinor * semiMinor)/2);
    }

    @Override
    public double getArea() {
        return Math.PI * semiMajor * semiMinor;
    }
    
    public boolean isCircle() {
        return semiMajor == semiMinor;
    }
    
    //New Methods
    public boolean isMajorAxisLarger() {
    	return semiMajor >= semiMinor;
    }
    public boolean isMinorAxisLarger() {
    	return semiMinor > semiMajor;
    }
}
