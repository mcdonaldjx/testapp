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
public class ShapeFactory {
    static Shape Create(String type, double [] input) {
        switch(type) {
            case "Ellipse":
                return new Ellipse(input);
            case "Rectangle":
                return new Rectangle(input);
            case "Triangle":
                return new Triangle(input);
            default:
                throw new IllegalArgumentException("Unsupported shape");
        }
    }
}
